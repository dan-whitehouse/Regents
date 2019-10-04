package org.neric.regents.model.form;

public class SelectedAction {
    private boolean selected;
    private String uuid;

    public SelectedAction() {
        super();
    }


    public SelectedAction(boolean selected, String uuid) {
        super();
        this.selected = selected;
        this.uuid = uuid;
    }


    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }


    public String getUuid() {
        return uuid;
    }


    public void setUuid(String uuid) {
        this.uuid = uuid;
    }


    @Override
    public String toString() {
        return "SelectedAction [selected=" + selected + ", uuid=" + uuid + "]";
    }

}
