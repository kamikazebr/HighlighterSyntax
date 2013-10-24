package main;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileFilter;

import main.interpreter.AbstractSyntaxInterpreter;

/**
 *The MIT License (MIT)
 *
 *	Copyright (c) [2011] [Felipe Novaes F. da Rocha]
 *	
 *	Permission is hereby granted, free of charge, to any person obtaining a copy of
 *	this software and associated documentation files (the "Software"), to deal in
 *	the Software without restriction, including without limitation the rights to
 *	use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 *	the Software, and to permit persons to whom the Software is furnished to do so,
 *	subject to the following conditions:
 *	
 *	The above copyright notice and this permission notice shall be included in all
 *	copies or substantial portions of the Software.
 *	
 *	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 *	FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 *	COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 *	IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 *	CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 * 
 *  
 * @author Kamikaze [Felipe Novaes F. da Rocha]
 * 
 */
public class HighlighterSyntax {

	public static final String regexMatchExtension = "(.*?\\.?.*?)(\\.\\w+)";

	/**
	 * @param args
	 * @throws UnsupportedLookAndFeelException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					UIManager.setLookAndFeel(UIManager
							.getSystemLookAndFeelClassName());

					final JFrame mainFrame = new JFrame();
					Container viewerPanel = new JPanel();

					// String currentDirectoryPath =
					// "C:\\Program Files (x86)\\Apoio\\Visualg Versão 2\\idade.alg";
					String currentDirectoryPath = System
							.getProperty("user.home");

					final String pathProperty = String.format("%s"
							+ File.separator + "highlightersyntax",
							System.getProperty("user.home"));
					final String fileProperty = String.format("hs.prop");
					final String pathAndFileProperty = pathProperty
							+ File.separator + fileProperty;

					final Properties properties = new Properties();
					try {
						File file = new File(pathProperty);
						File fileP = new File(pathAndFileProperty);
						if (!file.exists() && !fileP.exists()) {
							file.mkdirs();
							fileP.createNewFile();
							properties.setProperty("lastPath",
									currentDirectoryPath);
						} else {
							properties.load(new FileInputStream(fileP));
							currentDirectoryPath = properties
									.getProperty("lastPath");
						}
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}

					JFileChooser jFileChooser = new JFileChooser(
							currentDirectoryPath);

					FileFilterDynamic fileFilterDynamic = new FileFilterDynamic();

					Map<String, Class<? extends AbstractSyntaxInterpreter>> listExtensions = SyntaxInterpreterFactory
							.getListExtensions();

					Set<String> keySet = listExtensions.keySet();

					String[] a = {};
					String[] array = (String[]) keySet.toArray(a);

					fileFilterDynamic.setExtensions(array);

					jFileChooser.setFileFilter(fileFilterDynamic);

					jFileChooser.setMultiSelectionEnabled(true);

					jFileChooser.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							JFileChooser me = (JFileChooser) e.getSource();
							String actionCommand = e.getActionCommand();
							if (actionCommand
									.equals(JFileChooser.APPROVE_SELECTION)) {
								File[] selectedFiles = me.getSelectedFiles();
								for (File file : selectedFiles) {
									String absolutePath = file
											.getAbsolutePath();
									properties.put("lastPath", file.getParent());

									String extension = absolutePath.replaceAll(
											regexMatchExtension, "$2");
									AbstractSyntaxInterpreter syntaxInterpreter = SyntaxInterpreterFactory
											.getInstance(extension);
									System.out.println(syntaxInterpreter);
									syntaxInterpreter
											.initHightlighterSyntax(absolutePath);
								}
							}
							try {
								File file = new File(pathAndFileProperty);
								Writer out = new FileWriter(file);
								properties.store(out, "AHuahuahu");
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							mainFrame.dispose();
						}
					});
					viewerPanel.add(jFileChooser);

					mainFrame.setContentPane(viewerPanel);
					mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					mainFrame.setLocation(10, 10);
					mainFrame.pack();
					mainFrame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
