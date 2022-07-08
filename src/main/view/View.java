package main.view;

import main.controller.*;
import main.model.Category;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class View {

    private Map<Class<? extends Message>, Function<Message, String>> convertionMap = new HashMap<>();

    public View(){
        convertionMap.put(CategoryLongMessageForView.class, (e)-> this.getCategoryLongDescription((CategoryLongMessageForView) e));
        convertionMap.put(CategoryShortMessageForView.class, (e)-> this.getCategoryShortDescription((CategoryShortMessageForView) e));
        convertionMap.put(DayMessageForView.class, (e)->this.getDay((DayMessageForView) e));
        convertionMap.put(ExchangedOffersMessageForView.class, (e)->this.getExchangedOffersDescription((ExchangedOffersMessageForView) e));
        convertionMap.put(ExchangeMessageForView.class, (e)-> this.getExchangeInfo((ExchangeMessageForView) e));
        convertionMap.put(NodeMessageForView.class, (e)->this.getNodeDescription((NodeMessageForView) e));
        convertionMap.put(OfferMessageForView.class, (e)->this.getOfferInfo((OfferMessageForView) e));
        convertionMap.put(TimeMessageForView.class, (e)->this.getTimeDescription((TimeMessageForView) e));
        convertionMap.put(ErrorMessage.class, (e)->((ErrorMessage)e).getMessage());
        convertionMap.put(GenericMessage.class, (e)->((GenericMessage)e).getMessage());
        convertionMap.put(YesOrNoMessage.class, (e)->((YesOrNoMessage)e).getMessage());
        convertionMap.put(CustomMessage.class, (e)->((CustomMessage)e).getMessage());
    }

    public <T> void showList(@NotNull List<T> list, Function<T, Message> function) {
        list.forEach(e -> this.notify(function == null ? new CustomMessage(e.toString()) : function.apply(e)));
    }

    public <T> T choose(Message prompt, @NotNull List<T> selections, Function<T, String> function) {
        this.notify(prompt);
        return this.choose(selections, function);
    }

    public <T> T choose(@NotNull List<T> selections, Function<T, String> function) {
        assert selections.size() > 0;
        int i = 0;
        for (var o : selections)
            this.notify(i++ + ") " + (function == null ? o.toString() : function.apply(o)));

        int input = -1;
        IntegerReader integerReader = new IntegerReader();
        while ((input = integerReader.in(GenericMessage.SELECT_INDEX)) < 0 || input >= selections.size()) ;
        return selections.get(input);
    }

    private void printText(String text) {
        System.out.println(text);
    }

    public void notify(String text) {
        this.printText(text);
    }

    public void notify(@NotNull Message m) {
        this.notify(this.convertionMap.get(m.getClass()).apply(m));
    }

    public String getDay(DayMessageForView msg) {
        return msg.getDayName();
    }

    public void printDay(DayMessageForView msg) {
        this.notify(this.getDay(msg));
    }

    public String getExchangeInfo(ExchangeMessageForView msg){
        if (msg == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("\n\nInformazioni appuntamento per lo scambio:");
        sb.append("\nPiazza: " + msg.getPlace());
        sb.append("\nLuogo: " + msg.getAddress());
        sb.append("\nGiorno: " + msg.getDay());
        sb.append("\nOrario: " + msg.getTimeRange());
        return sb.toString();
    }

    public void printExchangeInfo(ExchangeMessageForView msg) {
        this.notify(this.getExchangeInfo(msg));
    }

    public String getExchangedOffersDescription(ExchangedOffersMessageForView msg) {
        return msg.getSelected().getName() + " <--> " + msg.getOwn().getName();
    }

    public void printExchangedOffersDescription(ExchangedOffersMessageForView msg) {
        this.notify(getExchangedOffersDescription(msg));
    }

    public String getOfferInfo(OfferMessageForView msg) {
        StringBuilder sb = new StringBuilder();
        sb.append("Offerta " + msg.getName());
        sb.append("\n\tCategoria > " + msg.getCategory().getShortDescription());
        sb.append("\n\tProprietario > " + msg.getOwner().getUsername());
        sb.append("\n\tStato > " + msg.getState().getState());
        sb.append("\n\tCampi > ");
        for (var valCampo : msg.getFieldsValues().entrySet()) {
            sb.append("\n\t\t").append(valCampo.getKey()).append("> ").append(valCampo.getValue());
        }
        sb.append("\n");

        return sb.toString();
    }

    public void printOfferInfo(OfferMessageForView msg) {
        this.notify(this.getOfferInfo(msg));
    }

    public String getCategoryShortDescription(CategoryShortMessageForView msg) {
        return "Categoria " + msg.getNome();
    }

    public void printCategoryShortDescription(CategoryShortMessageForView msg) {
        this.notify(this.getCategoryShortDescription(msg));
    }

    public void printCategoryLongDescription(CategoryLongMessageForView msg) {
        this.notify(this.getCategoryLongDescription(msg));
    }

    public String getCategoryLongDescription(CategoryLongMessageForView msg) {
        StringBuilder sb = new StringBuilder();
        sb.append("Nome: ").append(msg.getNome());
        sb.append("\nDescrizione: ").append(msg.getDescrizione());
        sb.append("\nCampi nativi:");
        for (String n : msg.getCampinativi().keySet()) {
            sb.append("\n-> " + n);
            sb.append(msg.getCampinativi().get(n).isObbligatorio() ? " (Obbligatorio)" : " (Falcotativo)");
        }
        return sb.toString();
    }

    public String getNodeDescription(NodeMessageForView msg) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getCategoryLongDescription(msg.getMsg()));
        if (msg.getCategorieFiglie().size() != 0) {
            sb.append("\n\nCategorie figlie di ").append(msg.getNome()).append(":");
            int i = 1;
            for (Category c : msg.getCategorieFiglie()) {
                sb.append("\n\n")
                        .append(i++).append(") ")
                        .append(this.getCategoryLongDescription((CategoryLongMessageForView) c.getCategoryDefinition()));
            }
        }

        return sb.toString();
    }

    public void printNodeDescription(NodeMessageForView msg) {
        this.notify(this.getNodeDescription(msg));
    }

    public String getTimeDescription(TimeMessageForView msg) {
        String hour = msg.getHour() == 0 ? "00" : String.valueOf(msg.getHour());
        String mins = msg.getMin() == 0 ? "00" : String.valueOf(msg.getMin());
        return hour + ":" + mins;
    }

    public void printTimeDescription(TimeMessageForView msg) {
        this.notify(this.getTimeDescription(msg));
    }
}
