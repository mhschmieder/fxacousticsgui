/*
 * MIT License
 *
 * Copyright (c) 2020, 2025 Mark Schmieder
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * This file is part of the FxAcoustics Library
 *
 * You should have received a copy of the MIT License along with the
 * FxAcoustics Library. If not, see <https://opensource.org/licenses/MIT>.
 *
 * Project: https://github.com/mhschmieder/fxacoustics
 */
package com.mhschmieder.fxacousticsgui.stage;

import com.mhschmieder.fxacousticsgui.layout.SplRangePane;
import com.mhschmieder.fxcontrols.action.SimulationActions;
import com.mhschmieder.fxcontrols.control.PredictToolBar;
import com.mhschmieder.fxgui.stage.XStage;
import com.mhschmieder.jcommons.branding.ProductBranding;
import com.mhschmieder.jcommons.util.ClientProperties;
import javafx.scene.Node;
import javafx.scene.control.ToolBar;

public final class SplRangeStage extends XStage {

    public static final String SPL_RANGE_FRAME_TITLE_DEFAULT
            = "Sound Field SPL Range"; //$NON-NLS-1$

    // Declare the actions.
    public SimulationActions simulationActions;

    // Declare the main tool bar.
    public PredictToolBar toolBar;

    // Declare the main content pane.
    public SplRangePane splRangePane;
    
    // Flag for whether to allow extended SPL Range values.
    protected final boolean useExtendedRange;

    public SplRangeStage( final ProductBranding pProductBranding,
                          final ClientProperties pClientProperties,
                          final boolean pUseExtendedRange ) {
        // Always call the superclass constructor first!
        super( SPL_RANGE_FRAME_TITLE_DEFAULT, 
               "splRange", 
               pProductBranding, 
               pClientProperties );
        
        useExtendedRange = pUseExtendedRange;

        try {
            initStage();
        }
        catch ( final Exception ex ) {
            ex.printStackTrace();
        }
    }

    public int getSplRangeDb() {
        // Forward this method to the SPL Range Pane.
        return splRangePane.getSplRangeDb();
    }

    private void initStage() {
        // First have the superclass initialize its content.
        initStage( "/icons/mhschmieder/JetPalette16.png", 
                   300.0d, 
                   180.0d,
                   false );
    }

    public boolean isAutoRangeSpl() {
        // Forward this method to the SPL Range Pane.
        return splRangePane.isAutoRangeSpl();
    }

    // Load the relevant actions for this Stage.
    @Override
    protected void loadActions() {
        // Make all of the actions.
        simulationActions = new SimulationActions( clientProperties );
    }

    @Override
    protected Node loadContent() {
        // Instantiate and return the custom Content Node.
        splRangePane = new SplRangePane( clientProperties, 
                                         useExtendedRange );
        return splRangePane;
    }

    // Add the Tool Bar for this Stage.
    @Override
    public ToolBar loadToolBar() {
        // Build the Tool Bar for this Stage.
        toolBar = new PredictToolBar( clientProperties, 
                                      simulationActions );

        // Return the Tool Bar so the superclass can use it.
        return toolBar;
    }

    public void updateSplRange( final boolean autoRangeSpl, 
                                final int splRangeDb ) {
        // Forward this method to the SPL Range Pane.
        splRangePane.updateSplRange( autoRangeSpl, 
                                     splRangeDb );
    }
}
