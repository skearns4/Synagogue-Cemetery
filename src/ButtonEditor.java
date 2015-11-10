/**
 * Created by brittanyregrut on 11/2/15.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Extends DefaultCellEditor:
 * The default editor for table and tree cells.
 */
public class ButtonEditor extends DefaultCellEditor
{
    protected JButton button;
    private String label;
    private boolean isPushed;

    /**
     * ButtonEditor constructor -- calls super(), sets visible, adds Listener
     *
     * @param checkBox box from selected row
     */
    public ButtonEditor(JCheckBox checkBox)
    {
        super(checkBox);
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                fireEditingStopped();
            }
        });
    }

    /**
     * Sets an initial value for the editor.
     * This will cause the editor to stopEditing and lose any partially edited value
     * if the editor is editing when this method is called.
     *
     * @param table the JTable that is asking the editor to edit; can be null
     * @param value the value of the cell to be edited
     * @param isSelected true if the cell is to be rendered with highlighting
     * @param row the row of the cell being edited
     * @param column the column of the cell being edited
     * @return the component for editing
     */
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column)
    {
        if (isSelected)
        {
            button.setForeground(table.getSelectionForeground());
            button.setBackground(table.getSelectionBackground());
        }
        else
        {
            button.setForeground(table.getForeground());
            button.setBackground(table.getBackground());
        }
        label = (value == null) ? "" : value.toString();
        button.setText(label);
        isPushed = true;
        return button;
    }

    /**
     * Returns the value contained in the editor.
     *
     * @return the value contained in the editor
     */
    public Object getCellEditorValue()
    {
        if (isPushed)
        {
            JPanel test = new JPanel();
            JLabel testText = new JLabel("Test");
            test.add(testText);
            JFrame newWindow = new JFrame("test");
            newWindow.getContentPane().add(test);
            newWindow.pack();
            newWindow.setVisible(true);

        }
        isPushed = false;
        return new String(label);
    }

    /**
     * Tells the editor to stop editing and accept any partially edited value as the value of the editor.
     *
     * @return true if editing was stopped; false otherwise
     */
    public boolean stopCellEditing()
    {
        isPushed = false;
        return super.stopCellEditing();
    }

    /**
     * Notifies all listeners that have registered interest for notification on this event type
     */
    protected void fireEditingStopped()
    {
        super.fireEditingStopped();
    }
}