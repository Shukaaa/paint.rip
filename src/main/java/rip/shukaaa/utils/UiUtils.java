package rip.shukaaa.utils;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSpinnerUI;
import java.awt.*;

public class UiUtils {
		public static JLabel createLabelTitle(String title) {
				JLabel label = new JLabel(title);
				// add bold
				label.setFont(label.getFont().deriveFont(14.0f).deriveFont(Font.ITALIC));
				label.setBorder(BorderFactory.createEmptyBorder(0, 8, 4, 0));
				return label;
		}

		public static JDialog createDialog(String title) {
				JDialog dialog = new JDialog();
				dialog.setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS));
				dialog.setTitle(title);
				dialog.setVisible(true);
				return dialog;
		}

		public static JSpinner createSpinner(int value, int min, int max, String title) {
				SpinnerModel spinnerModel = new SpinnerNumberModel(value, min, max, 1);
				JSpinner spinner = new JSpinner(spinnerModel);
				spinner.setBorder(BorderFactory.createTitledBorder(title));
				spinner.setUI(new BasicSpinnerUI() {
						protected Component createNextButton() {
								return null;
						}

						protected Component createPreviousButton() {
								return null;
						}
				});
				return spinner;
		}
}
