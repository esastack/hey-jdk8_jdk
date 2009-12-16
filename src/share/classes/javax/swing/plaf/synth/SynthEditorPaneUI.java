/*
 * Copyright 2002-2008 Sun Microsystems, Inc.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Sun designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Sun in the LICENSE file that accompanied this code.
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
 * Please contact Sun Microsystems, Inc., 4150 Network Circle, Santa Clara,
 * CA 95054 USA or visit www.sun.com if you need additional information or
 * have any questions.
 */

package javax.swing.plaf.synth;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.plaf.*;
import javax.swing.plaf.basic.BasicEditorPaneUI;
import java.beans.PropertyChangeEvent;

/**
 * Provides the Synth L&F UI delegate for
 * {@link javax.swing.JEditorPane}.
 *
 * @author  Shannon Hickey
 * @since 1.7
 */
public class SynthEditorPaneUI extends BasicEditorPaneUI implements SynthUI {
    private SynthStyle style;
    /*
     * I would prefer to use UIResource instad of this.
     * Unfortunately Boolean is a final class
     */
    private Boolean localTrue = Boolean.TRUE;

    /**
     * Creates a new UI object for the given component.
     *
     * @param c component to create UI object for
     * @return the UI object
     */
    public static ComponentUI createUI(JComponent c) {
        return new SynthEditorPaneUI();
    }

    /**
     * @inheritDoc
     */
    @Override
    protected void installDefaults() {
        // Installs the text cursor on the component
        super.installDefaults();
        JComponent c = getComponent();
        Object clientProperty =
            c.getClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES);
        if (clientProperty == null) {
            c.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, localTrue);
        }
        updateStyle(getComponent());
    }

    /**
     * @inheritDoc
     */
    @Override
    protected void uninstallDefaults() {
        SynthContext context = getContext(getComponent(), ENABLED);
        JComponent c = getComponent();
        c.putClientProperty("caretAspectRatio", null);

        style.uninstallDefaults(context);
        context.dispose();
        style = null;

        Object clientProperty =
            c.getClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES);
        if (clientProperty == localTrue) {
            c.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES,
                                             Boolean.FALSE);
        }
        super.uninstallDefaults();
    }

    /**
     * This method gets called when a bound property is changed
     * on the associated JTextComponent.  This is a hook
     * which UI implementations may change to reflect how the
     * UI displays bound properties of JTextComponent subclasses.
     * This is implemented to rebuild the ActionMap based upon an
     * EditorKit change.
     *
     * @param evt the property change event
     */
    @Override
    protected void propertyChange(PropertyChangeEvent evt) {
        if (SynthLookAndFeel.shouldUpdateStyle(evt)) {
            updateStyle((JTextComponent)evt.getSource());
        }
        super.propertyChange(evt);
    }

    private void updateStyle(JTextComponent comp) {
        SynthContext context = getContext(comp, ENABLED);
        SynthStyle oldStyle = style;

        style = SynthLookAndFeel.updateStyle(context, this);

        if (style != oldStyle) {
            SynthTextFieldUI.updateStyle(comp, context, getPropertyPrefix());

            if (oldStyle != null) {
                uninstallKeyboardActions();
                installKeyboardActions();
            }
        }
        context.dispose();
    }

    /**
     * @inheritDoc
     */
    @Override
    public SynthContext getContext(JComponent c) {
        return getContext(c, getComponentState(c));
    }

    private SynthContext getContext(JComponent c, int state) {
        return SynthContext.getContext(SynthContext.class, c,
                    SynthLookAndFeel.getRegion(c), style, state);
    }

    private int getComponentState(JComponent c) {
        return SynthLookAndFeel.getComponentState(c);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void update(Graphics g, JComponent c) {
        SynthContext context = getContext(c);

        SynthLookAndFeel.update(context, g);
        paintBackground(context, g, c);
        paint(context, g);
        context.dispose();
    }

    /**
     * Paints the specified component.
     *
     * @param context context for the component being painted
     * @param g {@code Graphics} object used for painting
     */
    protected void paint(SynthContext context, Graphics g) {
        super.paint(g, getComponent());
    }

    /**
     * @inheritDoc
     */
    @Override
    protected void paintBackground(Graphics g) {
        // Overriden to do nothing, all our painting is done from update/paint.
    }

    void paintBackground(SynthContext context, Graphics g, JComponent c) {
        context.getPainter().paintEditorPaneBackground(context, g, 0, 0,
                                                  c.getWidth(), c.getHeight());
    }

    /**
     * @inheritDoc
     */
    @Override
    public void paintBorder(SynthContext context, Graphics g, int x,
                            int y, int w, int h) {
        context.getPainter().paintEditorPaneBorder(context, g, x, y, w, h);
    }
}
