package impl.com.pixelduke.skin.ribbon;

import com.pixelduke.control.ribbon.RibbonMenu;
import com.pixelduke.control.ribbon.RibbonTabPane;
import com.sun.javafx.scene.control.skin.ContextMenuContent;
import com.sun.javafx.scene.control.skin.TabPaneSkin;
import javafx.collections.ListChangeListener;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 * {@link RibbonMenu} can only be placed in first slot.
 */
public class RibbonTabPaneSkin extends TabPaneSkin {

    private static final String RIBBON_MENU_DEFAULT_STYLE_CLASS = "ribbon-menu";

    private ContextMenu popup;

    public RibbonTabPaneSkin(RibbonTabPane tabPane) {
        super(tabPane);
        Tab tab = getSkinnable().getTabs().get(0);
        if (tab instanceof RibbonMenu) {
            configureMenu((RibbonMenu) tab);
        }
    }

    private void configureMenu(RibbonMenu ribbonMenu) {
        popup = new ContextMenu();
        popup.getItems().clear();
        popup.getItems().addAll(ribbonMenu.getItems());
        ribbonMenu.getItems().addListener((ListChangeListener<? super MenuItem>) c -> {
            while (c.next()) {
                popup.getItems().removeAll(c.getRemoved());
                popup.getItems().addAll(c.getFrom(), c.getAddedSubList());
            }
        });
        popup.setOnShown(event -> {
            ContextMenuContent cmContent = (ContextMenuContent) popup.getSkin().getNode();
            if (cmContent != null) {
                cmContent.requestFocus();
            }
        });
        Label label = findLabel();
        ribbonMenu.showingProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                show(label);
            }
        });
        popup.showingProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                ribbonMenu.hide();
            }
        });
    }

    private void show(Label label) {
        if (!popup.isShowing()) {
            popup.show(label, Side.BOTTOM, 0, 0);
        }
    }

    private Label findLabel() {
        for (Node child : getChildren()) {
            if (child.getStyleClass().contains("tab-header-area")) {
                StackPane tabHeaderArea = (StackPane) child;
                for (Node tabHeaderAreaChild : tabHeaderArea.getChildren()) {
                    if (tabHeaderAreaChild.getStyleClass().contains("headers-region")) {
                        StackPane headersRegion = (StackPane) tabHeaderAreaChild;
                        StackPane tab = (StackPane) headersRegion.getChildren().get(0);
                        tab.getStyleClass().remove("tab");
                        tab.getStyleClass().add(RIBBON_MENU_DEFAULT_STYLE_CLASS);
                        Pane tabContainer = (Pane) tab.getChildren().get(0);
                        return (Label) tabContainer.getChildren().get(0);
                    }
                }
            }
        }
        return null;
    }

}
