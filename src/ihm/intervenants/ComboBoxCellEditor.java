package ihm.intervenants;

import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;


public class ComboBoxCellEditor extends AbstractCellEditor implements TableCellEditor{
	private JComboBox<String> box;
	private String[] tabStatut;

	public ComboBoxCellEditor(){
		this.box = new JComboBox<String>(this.tabStatut);
	}
	@Override
	public Object getCellEditorValue() {
		return box.getSelectedItem(); 
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		box.setSelectedItem(value);
		return box;
	}
	
}
