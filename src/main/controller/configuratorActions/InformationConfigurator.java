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

public class InformationConfigurator implements UserSelectable {
    @Override
    public void runAction(Controller controller, User user) throws IOException {

        if (this.getInfo(controller.getApp()) == null) {
            writeInfos(controller, controller.askStringFromView(GenericMessage.PLACE));

        } else {
            Controller.signalToView(GenericMessage.CURRENT_INFO.getMessage());
            this.getInfo(controller.getApp()).print();

            if (Controller.askBooleanFromView(YesOrNoMessage.OVERWRITE_INFO))
                writeInfos(controller, this.getInfo(controller.getApp()).getPlace());

        }

        controller.getApp().getInformationStore().save();

        //se non modifico le informazioni di scambio e conf.json è corrotto/incompleto qui viene
        //sovrascritto con le informazioni correnti complete e non modificate
        controller.signalToView(GenericMessage.SAVED_CORRECTLY.getMessage());
    }

    private void writeInfos(Controller controller, String place){
        this.setInfo(controller.getApp(), new Information(
                place,
                setupAddresses(controller),
                setupDays(controller),
                setupTimeRanges(controller),
                controller.askIntFromView(GenericMessage.DEADLINE)));
    }

    @NotNull
    private List<TimeRange> setupTimeRanges(Controller controller) {
        List<TimeRange> timeRanges = new LinkedList<>();
        while (timeRanges.isEmpty() || controller.askBooleanFromView(YesOrNoMessage.ADD_TIMERANGE)) {
            controller.signalToView(GenericMessage.EXCHANGE_HOURS_EVERY_30_MINS.getMessage());

            TimeRange tr = new TimeRange(
                    this.askTime(GenericMessage.STARTING_HOUR, GenericMessage.STARTING_MINUTES, controller),
                    this.askTime(GenericMessage.ENDING_HOUR, GenericMessage.ENDING_MINUTES, controller)
            );

            if (tr.isValidRange() && tr.isNewRange(timeRanges))
                timeRanges.add(tr);
            else
                controller.signalToView(ErrorMessage.E_INVALID_TIME_RANGE.getMessage());
        }
        return timeRanges;
    }

    public Time askTime(Message startMessageH, Message startMessageM, Controller controller) {
        Time hour;
        int h;
        int m;
        do {
            h = controller.askIntFromView(startMessageH);
            m = controller.askIntFromView(startMessageM);
            hour = new Time(h, m);
            if (!hour.isValid(h, m))
                controller.signalToView(ErrorMessage.E_INVALID_TIME.getMessage());
        } while (!hour.isValid(h, m));
        return hour;
    }

    @NotNull
    private List<Day> setupDays(Controller controller) {
        List<Day> days = new LinkedList<>();
        while (days.isEmpty() || controller.askBooleanFromView(YesOrNoMessage.ADD_DAY)) {
            Day g = controller.getView().choose(GenericMessage.DAY, Arrays.stream(Day.values()).collect(Collectors.toList()), Day::getDay);
            if (g != null && !days.contains(g))
                days.add(g);
        }
        return days;
    }

    @NotNull
    private List<String> setupAddresses(Controller controller) {
        List<String> addresses = new LinkedList<>();
        while (addresses.isEmpty() || controller.askBooleanFromView(YesOrNoMessage.ADD_ADDRESS)) {
            addresses.add(controller.askStringFromView(GenericMessage.ADDRESS));
        }
        return addresses;
    }

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
