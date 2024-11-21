package com.pixelduke.control.ribbon;

import impl.com.pixelduke.skin.ribbon.RibbonTabPaneSkin;
import javafx.collections.ListChangeListener;
import javafx.scene.control.Skin;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class RibbonTabPane extends TabPane {

    private boolean menuActive = false;

    public RibbonTabPane() {
        getTabs().addListener((ListChangeListener<? super Tab>) c -> {
            if (getTabs().size() == 2 && getTabs().get(0) instanceof RibbonMenu) {
                getSelectionModel().select(getTabs().get(1));
                menuActive = true;
            }
        });
        getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue instanceof RibbonMenu) {
                RibbonMenu ribbonMenu = (RibbonMenu) newValue;
                if (menuActive) {
                    getSelectionModel().select(oldValue);
                    ribbonMenu.show();
                }
            }
        });
    }


    @Override
    protected Skin<?> createDefaultSkin() {
        return new RibbonTabPaneSkin(this);
    }

}
