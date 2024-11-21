package com.pixelduke.control.ribbon;

import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.scene.control.ComboBoxBase;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

public class RibbonMenu extends RibbonTab {

    private final ObservableList<MenuItem> items = FXCollections.<MenuItem>observableArrayList();
    /**
     * Indicates whether the {@link ContextMenu} is currently visible.
     */
    private final ReadOnlyBooleanWrapper showing = new ReadOnlyBooleanWrapper(this, "showing", false);

    public RibbonMenu() {
    }

    public RibbonMenu(String title) {
        super(title);
    }

    /**
     * The items to show within this buttons menu. If this ObservableList is modified
     * at runtime, the Menu will update as expected.
     * <p>
     * Commonly used controls include including {@code MenuItem},
     * {@code CheckMenuItem}, {@code RadioMenuItem},
     * and of course {@code Menu}, which if added to a menu, will become a sub
     * menu. {@link SeparatorMenuItem} is another commonly used Node in the Menu's items
     * ObservableList.
     *
     * @return MenuItems
     */
    public final ObservableList<MenuItem> getItems() {
        return items;
    }

    @Override
    public ObservableList<RibbonGroup> getRibbonGroups() {
        throw new IllegalAccessError("not supported for RibbonMenu");
    }

    /**
     * Shows the {@link ContextMenu}, assuming this MenuButton is not disabled.
     *
     * @see #isDisabled()
     * @see #isShowing()
     */
    public void show() {
        if (!isDisabled() && !showing.isBound()) {
            setShowing(true);
        }
    }

    public final boolean isShowing() {
        return showing.get();
    }

    private void setShowing(boolean value) {
        // these events will not fire if the showing property is bound
        Event.fireEvent(this, value ? new Event(ComboBoxBase.ON_SHOWING) :
                new Event(ComboBoxBase.ON_HIDING));
        showing.set(value);
        Event.fireEvent(this, value ? new Event(ComboBoxBase.ON_SHOWN) :
                new Event(ComboBoxBase.ON_HIDDEN));
    }

    public final ReadOnlyBooleanProperty showingProperty() {
        return showing.getReadOnlyProperty();
    }

    /**
     * Hides the {@link ContextMenu}.
     *
     * @see #isShowing()
     */
    public void hide() {
        if (!showing.isBound()) {
            setShowing(false);
        }
    }

}
