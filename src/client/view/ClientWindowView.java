package client.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import client.controller.ClientWindowController;
import client.model.Parameters;
import client.view.FacialPanel;

/**
 * Class client window view which inherited from JFrame
 * Initialize the window and other java components
 * @author Group10
 * @version 1.0
 */
public class ClientWindowView extends JFrame {
	private ClientWindowController ctrl;
	private FacialPanel facialPanel;
	private PlotPanel plotPanel;
	private PerformancePanel performPanel;
	private StatusPanel statusPanel;
	
	/**
      * create and initialize the menu bar of the window
      */
	public void initMenu() {
		JMenuBar menuBar;
		JMenu menu;
		JMenuItem menuItem;
		
		//Create the menu bar.
		menuBar = new JMenuBar();
		
		//Build Server menu.
		menu = new JMenu("Server");
		menu.setMnemonic(KeyEvent.VK_V);
		menu.getAccessibleContext().setAccessibleDescription(
				"The server menu in this window");
		menuBar.add(menu);
		
		menuItem = new JMenuItem("Open Server", KeyEvent.VK_O);
		menuItem.addMouseListener(new MouseAdapter() {
			@Override
            public void mousePressed(MouseEvent event) {
             	ServerTrigger serverTrigger = new ServerTrigger(ctrl);
            }
		});
		menuItem.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_O, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription(
				"Open server");
		menu.add(menuItem);
		
		//Build the Connection menu.
		menu = new JMenu("Connetion");
		menu.setMnemonic(KeyEvent.VK_C);
		menu.getAccessibleContext().setAccessibleDescription(
				"The connection menu in this window");
		menuBar.add(menu);
		
		//a group of JMenuItems
		menuItem = new JMenuItem("Settings",
				KeyEvent.VK_S);
		menuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent event) {
				ClientSocketDialog clientSocketDialog = new ClientSocketDialog(ctrl);
			}
		});
		menuItem.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_S, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription(
				"Open dialog of connection settins");
		menu.add(menuItem);
		
		menu = new JMenu("Help");
		menu.setMnemonic(KeyEvent.VK_H);
		menuBar.add(menu);
		
		menuItem = new JMenuItem("About Program", KeyEvent.VK_A);
		menuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent event) {
				JOptionPane.showMessageDialog(null,
						"Project 3 Team 10",
						"About Program",
						JOptionPane.PLAIN_MESSAGE);
			}
		});
		menu.add(menuItem);
		menuBar.add(menu);
		
		statusPanel = new StatusPanel();
		menuBar.add(statusPanel);
		
		this.setJMenuBar(menuBar);
	}
	
    /**
      * Init tabs method
      */
	public void initTabs() {
		JTabbedPane myTabPane = new JTabbedPane();
		JPanel facialExpressionPanel = new JPanel();
		performPanel = new PerformancePanel("Performance");
		myTabPane.addTab("Facial Expressions", facialExpressionPanel);
		myTabPane.addTab("Performance Metrics", performPanel);

		this.add(myTabPane, BorderLayout.CENTER);
		facialPanel = new FacialPanel();
		plotPanel = new PlotPanel();
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                facialPanel, plotPanel);
		splitPane.setDividerLocation(400);
		splitPane.setResizeWeight(.7d);
		facialExpressionPanel.setLayout(new BorderLayout());
		facialExpressionPanel.add(splitPane);
	}
	
	/**
	 * Initialize the tabbedpannel and add two panels as tab
	 * @param facialExpressionPanel
	 * @param perfomPanel
	 */
	public void initTabs(JPanel facialExpressionPanel, JPanel perfomPanel) {
		JTabbedPane myTabPane = new JTabbedPane();
		myTabPane.addTab("Facial Expressions", facialExpressionPanel);
		myTabPane.addTab("Performance Metrics", performPanel);

		this.add(myTabPane, BorderLayout.CENTER);
	}
	
	/**
	 * Initialize the panel and split the panel for two panels
	 * @param facialPanel
	 * @param plotPanel
	 */
	public void initSplitPanel(JPanel facialPanel, JPanel plotPanel) {
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                facialPanel, plotPanel);
		splitPane.setDividerLocation(400);
		splitPane.setResizeWeight(.7d);
	}
	
	/**
	 * Initialize the components of the window
	 */
	public ClientWindowView() {
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		    	setTitle("Lab3 Team 10 Control Panel");
				setSize(800, 600);
				setLocationByPlatform(true);
				setLayout(new BorderLayout());
				initMenu();
				initTabs();
				setVisible(true);
		    }
		});
	}
	
	/**
	 * Bind the controller of the view
	 * @param controller
	 */
	public void bindController(ClientWindowController controller) {
		this.ctrl = controller;
	}
	
	/**
	 * update client view 
	 * @param param
	 */
	public void update(Parameters param) {
		facialPanel.setData(param);
		plotPanel.add(param);
		performPanel.add(param);
		statusPanel.setStatus(2, param.getTime());
	}
	
	/**
	 * set the status of the status panel
	 * @param status
	 * @param time
	 */
	public void setStatus(int status, double time) {
		statusPanel.setStatus(status, time);
	}
}
