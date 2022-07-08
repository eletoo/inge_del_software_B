package main.controller.configuratorActions;

import main.model.Application;
import main.controller.*;
import main.model.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * configura le informazioni di scambio
 *
 * @author Elena Tonini, Claudia Manfredi, Mattia Pavlovic
 */
public class InformationConfigurator implements UserSelectable {
    /**
     * configura le informazioni di scambio
     *
     * @param controller controller
     * @param user       utente
     * @throws IOException eccezione I/O
     */
    @Override
    public void runAction(@NotNull Controller controller, User user) throws IOException {

        if (this.getInfo(controller.getApp()) == null) {
            writeInfos(controller, controller.askLineFromView(GenericMessage.PLACE));

        } else {
            controller.signalToView(GenericMessage.CURRENT_INFO);
            controller.signalToView(this.getInfo(controller.getApp()).getInformations());

            if (controller.askBooleanFromView(YesOrNoMessage.OVERWRITE_INFO))
                writeInfos(controller, this.getInfo(controller.getApp()).getPlace());

        }

        controller.getApp().getInformationStore().save();

        //se non modifico le informazioni di scambio e conf.json è corrotto/incompleto qui viene
        //sovrascritto con le informazioni correnti complete e non modificate
        controller.signalToView(GenericMessage.SAVED_CORRECTLY);
    }

    /**
     * sovrascrive le informazioni di scambio
     *
     * @param controller controller
     * @param place      piazza
     */
    private void writeInfos(@NotNull Controller controller, String place) {
        this.setInfo(controller.getApp(), new Information(
                place,
                setupAddresses(controller),
                setupDays(controller),
                setupTimeRanges(controller),
                controller.askIntFromView(GenericMessage.DEADLINE)));
    }

    /**
     * imposta gli intervalli orari
     *
     * @param controller controller
     * @return intervalli orari
     */
    @NotNull
    private List<TimeRange> setupTimeRanges(Controller controller) {
        List<TimeRange> timeRanges = new LinkedList<>();
        while (timeRanges.isEmpty() || controller.askBooleanFromView(YesOrNoMessage.ADD_TIMERANGE)) {
            controller.signalToView(GenericMessage.EXCHANGE_HOURS_EVERY_30_MINS);

            TimeRange tr = new TimeRange(
                    this.askTime(GenericMessage.STARTING_HOUR, GenericMessage.STARTING_MINUTES, controller),
                    this.askTime(GenericMessage.ENDING_HOUR, GenericMessage.ENDING_MINUTES, controller)
            );

            if (tr.isValidRange() && tr.isNewRange(timeRanges))
                timeRanges.add(tr);
            else
                controller.signalToView(ErrorMessage.E_INVALID_TIME_RANGE);
        }
        return timeRanges;
    }

    /**
     * chiede il singolo orario
     *
     * @param startMessageH ora
     * @param startMessageM minuti
     * @param controller    controller
     * @return orario
     */
    public Time askTime(Message startMessageH, Message startMessageM, @NotNull Controller controller) {
        Time hour;
        int h;
        int m;
        do {
            h = controller.askIntFromView(startMessageH);
            m = controller.askIntFromView(startMessageM);
            hour = new Time(h, m);
            if (!hour.isValid(h, m))
                controller.signalToView(ErrorMessage.E_INVALID_TIME);
        } while (!hour.isValid(h, m));
        return hour;
    }

    /**
     * chiede i giorni della settimana
     *
     * @param controller controller
     * @return giorni per lo scambio
     */
    @NotNull
    private List<Day> setupDays(Controller controller) {
        List<Day> days = new LinkedList<>();
        while (days.isEmpty() || controller.askBooleanFromView(YesOrNoMessage.ADD_DAY)) {
            Day g = controller.getView().choose(
                    GenericMessage.DAY,
                    Arrays.stream(Day.values()).collect(Collectors.toList()),
                    d -> new CustomMessage(d.getDay())
            );
            if (g != null && !days.contains(g))
                days.add(g);
        }
        return days;
    }

    /**
     * chiede i luoghi per lo scambio
     *
     * @param controller controller
     * @return luoghi
     */
    @NotNull
    private List<String> setupAddresses(Controller controller) {
        List<String> addresses = new LinkedList<>();
        while (addresses.isEmpty() || controller.askBooleanFromView(YesOrNoMessage.ADD_ADDRESS)) {
            addresses.add(controller.askLineFromView(GenericMessage.ADDRESS));
        }
        return addresses;
    }

    /**
     * @return descrizione dell'azione
     */
    @Override
    public String getActionName() {
        return "Configura informazioni di scambio";
    }

    private Information getInfo(@NotNull Application app) {
        return app.getInformationStore().getInformation();
    }

    private void setInfo(@NotNull Application app, Information info) {
        app.getInformationStore().setInformations(info);
    }

}
