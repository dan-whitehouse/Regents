package org.neric.regents.wizard;

import org.neric.regents.model.OptionPrint;

public class XOptionPrintWrapper {
    private OptionPrint optionPrint;

    public XOptionPrintWrapper(OptionPrint optionPrint) {
        this.optionPrint = optionPrint;
    }

    public XOptionPrintWrapper() {

    }

    public OptionPrint getOptionPrint() {
        return optionPrint;
    }

    public void setOptionPrint(OptionPrint optionPrint) {
        this.optionPrint = optionPrint;
    }

    @Override
    public String toString() {
        return "XOptionPrintWrapper [optionPrint=" + optionPrint + "]";
    }
}
