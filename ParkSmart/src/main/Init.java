package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Init {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Choose Role");
		JButton userButton = new JButton("User");
		JButton managerButton = new JButton("Manager");

		userButton.addActionListener(() -> {
			frame.dispose();
			Manager.launch();
		});
		managerButton.addActionListener((ActionEvent e) -> {				
			frame.dispose();
			Manager.launch();
		});

		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.add(userButton);
		panel.add(managerButton);

		frame.add(panel);
		frame.setSize(300, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}