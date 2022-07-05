package main.controller.configuratorActions;

import main.Application;
import main.controller.Controller;
import main.controller.GenericMessage;
import main.controller.Selectable;
import main.model.Information;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class InformationConfigurator implements Selectable {
    @Override
    public void runAction(@NotNull Application app) throws IOException {
        //todo: implementare

        if (this.getInfo(app) == null) {
            app.getInformationStore().setInformations(new Information(app, view));
        } else {
            Controller.signalToView(GenericMessage.CURRENT_INFO.getMessage());
            this.getInfo(app).print();

            if (view.yesOrNoQuestion("\nSovrascrivere le informazioni di scambio presenti (N.B. La piazza non è modificabile)? [Y/N]")) {
                app.getInformationStore().setInformations(new Information(app, view));
            }
        }
        app.saveInfo();//se non modifico le informazioni di scambio e conf.json è corrotto/incompleto qui viene
        //sovrascritto con le informazioni correnti complete e non modificate
        view.interactionMessage(View.InteractionMessage.SAVED_CORRECTLY);
    }

    @Override
    public String getActionName() {
        return "Configura informazioni di scambio";
    }

    private Information getInfo(@NotNull Application app) {
        return app.getInformationStore().getInformation();
    }
}
