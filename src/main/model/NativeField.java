package main.model;

import java.io.Serializable;
import java.util.function.Function;

public class NativeField implements Serializable {
    private boolean obbligatorio;
    private Tipo type;

    /**
     * Costruttore.
     *
     * @param obbligatorio true se il campo e' a compilazione obbligatoria
     * @param type         tipo del campo (di default e' String)
     */
    public NativeField(boolean obbligatorio, Tipo type) {
        this.obbligatorio = obbligatorio;
        this.type = type;
    }

    /**
     * @return true se il campo a compilazione obbligatoria
     */
    public boolean isObbligatorio() {
        return obbligatorio;
    }

    /**
     * @param obbligatorio true se il campo nativo e' a compilazione obbligatoria
     */
    public void setObbligatorio(boolean obbligatorio) {
        this.obbligatorio = obbligatorio;
    }

    /**
     * @return tipo del campo
     */
    public Tipo getType() {
        return type;
    }

    /**
     * @param type tipo del campo nativo (di default e' String)
     */
    public void setType(Tipo type) {
        this.type = type;
    }

    /**
     * Enum dei possibili tipi dei campi.
     */
    public static enum Tipo {
        STRING((s) -> {
            assert s instanceof String;
            return s;
        }, (o) -> o.toString());

        private Function<String, Object> des;
        private Function<Object, String> ser;

        Tipo(Function<String, Object> des, Function<Object, String> ser) {
            this.des = des;
            this.ser = ser;
        }

        public String serialize(Object o) {
            return this.ser.apply(o);
        }

        public Object deserialize(String o) {
            return this.des.apply(o);
        }
    }

}
