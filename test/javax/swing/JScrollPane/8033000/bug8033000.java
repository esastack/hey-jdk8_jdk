/*
 * Copyright (c) 2015, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import sun.awt.OSInfo;

/**
 * @test
 * @bug 8033000
 * @author Alexander Scherbatiy
 * @summary No Horizontal Mouse Wheel Support In BasicScrollPaneUI
 * @run main bug8033000
 */
public class bug8033000 {

    private static JScrollPane scrollPane;
    private static JTextArea textArea;
    private static Point point;
    private static final int delta;

    static {
        delta = OSInfo.getOSType().equals(OSInfo.OSType.MACOSX) ? -30 : 30;
    }

    public static void main(String[] args) throws Exception {

        Robot robot = new Robot();
        robot.setAutoDelay(50);

        SwingUtilities.invokeAndWait(bug8033000::createAndShowGUI);
        robot.waitForIdle();

        SwingUtilities.invokeAndWait(() -> {
            Point locationOnScreen = scrollPane.getLocationOnScreen();
            point = new Point(
                    locationOnScreen.x + scrollPane.getWidth() / 2,
                    locationOnScreen.y + scrollPane.getHeight() / 2);
        });

        robot.mouseMove(point.x, point.y);
        robot.waitForIdle();

        // vertical scroll bar is enabled
        initScrollPane(true, false);
        robot.waitForIdle();
        robot.mouseWheel(delta);
        robot.waitForIdle();
        checkScrollPane(true);

        // vertical scroll bar is enabled + shift
        initScrollPane(true, false);
        robot.waitForIdle();
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.mouseWheel(delta);
        robot.keyRelease(KeyEvent.VK_SHIFT);
        robot.waitForIdle();
        checkScrollPane(true);

        // horizontal scroll bar is enabled
        initScrollPane(false, true);
        robot.waitForIdle();
        robot.mouseWheel(delta);
        robot.waitForIdle();
        checkScrollPane(false);

        // horizontal scroll bar is enabled + shift
        initScrollPane(false, true);
        robot.waitForIdle();
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.mouseWheel(delta);
        robot.keyRelease(KeyEvent.VK_SHIFT);
        robot.waitForIdle();
        checkScrollPane(false);

        // both scroll bars are enabled
        initScrollPane(true, true);
        robot.waitForIdle();
        robot.mouseWheel(delta);
        robot.waitForIdle();
        checkScrollPane(true);

        // both scroll bars are enabled + shift
        initScrollPane(true, true);
        robot.waitForIdle();
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.mouseWheel(delta);
        robot.keyRelease(KeyEvent.VK_SHIFT);
        robot.waitForIdle();
        checkScrollPane(false);
    }

    static void initScrollPane(boolean vVisible, boolean hVisible) throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            scrollPane.getVerticalScrollBar().setValue(0);
            scrollPane.getHorizontalScrollBar().setValue(0);

            textArea.setRows(vVisible ? 100 : 1);
            textArea.setColumns(hVisible ? 100 : 1);
            scrollPane.getVerticalScrollBar().setVisible(vVisible);
            scrollPane.getHorizontalScrollBar().setVisible(hVisible);
        });
    }

    static void checkScrollPane(boolean verticalScrolled) throws Exception {
        SwingUtilities.invokeAndWait(() -> {

            if (verticalScrolled) {
                if (scrollPane.getVerticalScrollBar().getValue() == 0
                        || scrollPane.getHorizontalScrollBar().getValue() != 0) {
                    throw new RuntimeException("Wrong vertical scrolling!");
                }
            } else {
                if (scrollPane.getVerticalScrollBar().getValue() != 0
                        || scrollPane.getHorizontalScrollBar().getValue() == 0) {
                    throw new RuntimeException("Wrong horizontal scrolling!");
                }
            }
        });
    }

    static void createAndShowGUI() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        textArea = new JTextArea("Hello World!");
        scrollPane = new JScrollPane(textArea);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }
}
