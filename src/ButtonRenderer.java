/**
 * Created by brittanyregrut on 11/2/15.
 */
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

/**
 *  Implements interface that defines the method required by any object
 *  that would like to be a renderer for cells in a JTable.
 */
public class ButtonRenderer extends JButton implements TableCellRenderer {

    /**
     * ButtonRenderer constructor -- sets visible
     */
    public ButtonRenderer() {
        setOpaque(true);
    }

    /**
     * Returns the component used for drawing the cell.
     * This method is used to configure the renderer appropriately before drawing.
     *
     * @param table JTable that is asking the renderer to draw; can be null
     * @param value value of cell to be rendered
     * @param isSelected  true if the cell is to be rendered with the selection highlighted; otherwise false
     * @param hasFocus if true, render cell appropriately
     * @param row the row index of the cell being drawn
     * @param column the column index of the cell being drawn
     * @return this
     */
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected) {
            setForeground(table.getSelectionForeground());
            setBackground(table.getSelectionBackground());
        } else {
            setForeground(table.getForeground());
            setBackground(UIManager.getColor("Button.background"));
        }
        setText((value == null) ? "" : value.toString());
        return this;
    }
}