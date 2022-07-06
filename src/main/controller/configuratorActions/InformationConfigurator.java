package main.controller.configuratorActions;

import main.Application;
import main.controller.*;
import main.exceptions.InvalidMethodException;
import main.model.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class InformationConfigurator implements UserSelectable, ListSelect {
    @Override
    public void runAction(@NotNull Application app, Controller controller, User user) throws IOException {

        if (this.getInfo(app) == null) {
            this.setInfo(app, new Information(Controller.askStringFromView(GenericMessage.PLACE),
                    setupAddresses(),
                    setupDays(),
                    setupTimeRanges(),
                    Controller.askIntFromView(GenericMessage.DEADLINE)));

        } else {
            Controller.signalToView(GenericMessage.CURRENT_INFO.getMessage());
            this.getInfo(app).print();

            if (Controller.askBooleanFromView(YesOrNoMessage.OVERWRITE_INFO)) {
                this.setInfo(app, new Information(this.getInfo(app).getPlace(),
                        setupAddresses(),
                        setupDays(),
                        setupTimeRanges(),
                        Controller.askIntFromView(GenericMessage.DEADLINE)));
            }
        }

        app.getInformationStore().save();

        //se non modifico le informazioni di scambio e conf.json è corrotto/incompleto qui viene
        //sovrascritto con le informazioni correnti complete e non modificate
        Controller.signalToView(GenericMessage.SAVED_CORRECTLY.getMessage());
    }

    @NotNull
    private List<TimeRange> setupTimeRanges() {
        List<TimeRange> timeRanges = new LinkedList<>();
        while (timeRanges.isEmpty() || Controller.askBooleanFromView(YesOrNoMessage.ADD_TIMERANGE)) {
            Controller.signalToView(GenericMessage.EXCHANGE_HOURS_EVERY_30_MINS.getMessage());

            TimeRange tr = new TimeRange(Time.askStartingTime(), Time.askEndingTime());

            if (tr.isValidRange() && tr.isNewRange(timeRanges))
                timeRanges.add(tr);
            else
                Controller.signalToView(ErrorMessage.E_INVALID_TIME_RANGE.getMessage());
        }
        return timeRanges;
    }

    @NotNull
    private List<Day> setupDays() {
        List<Day> days = new LinkedList<>();
        while (days.isEmpty() || Controller.askBooleanFromView(YesOrNoMessage.ADD_DAY)) {
            Controller.signalToView(GenericMessage.DAY.getMessage());
            Day g = this.choose(Arrays.stream(Day.values()).collect(Collectors.toList()), Day::getDay);
            if (g != null && !days.contains(g))
                days.add(g);
        }
        return days;
    }

    @NotNull
    private List<String> setupAddresses() {
        List<String> addresses = new LinkedList<>();
        while (addresses.isEmpty() || Controller.askBooleanFromView(YesOrNoMessage.ADD_ADDRESS)) {
            addresses.add(Controller.askStringFromView(GenericMessage.ADDRESS));
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
