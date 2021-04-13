package base;

import login.LoginFrame;
import publications.PublicationFrame;
import report.ReportFrame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Customer.CustomerFrame;
import DeliveryOrder.DeliveryOrder;
import DeliveryOrder.DeliveryOrderFrame;
import OrderReport.OrderReportFrame;
import biling.BillFrame;

public class GUIbase {

	public static void main(String[] args) {
		login.Main.main(null);
	}
}