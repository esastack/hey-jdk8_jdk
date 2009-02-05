/*
 * Copyright 2007 Sun Microsystems, Inc.  All Rights Reserved.
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

/* @test
   @summary Test SimpleInstrument setPatch(Patch) method */

import javax.sound.midi.Patch;
import javax.sound.sampled.*;

import com.sun.media.sound.*;

public class SetPatch {

    private static void assertEquals(Object a, Object b) throws Exception
    {
        if(!a.equals(b))
            throw new RuntimeException("assertEquals fails!");
    }

    public static void main(String[] args) throws Exception {

        SimpleInstrument instrument = new SimpleInstrument();

        ModelPerformer[] performers = new ModelPerformer[2];

        performers[0] = new ModelPerformer();
        performers[0].setExclusiveClass(1);
        performers[0].setKeyFrom(36);
        performers[0].setKeyTo(48);
        performers[0].setVelFrom(16);
        performers[0].setVelTo(80);
        performers[0].setSelfNonExclusive(true);
        performers[0].setDefaultConnectionsEnabled(false);
        performers[0].getConnectionBlocks().add(new ModelConnectionBlock());
        performers[0].getOscillators().add(new ModelByteBufferWavetable(new ModelByteBuffer(new byte[] {1,2,3})));

        performers[1] = new ModelPerformer();
        performers[1].setExclusiveClass(0);
        performers[1].setKeyFrom(12);
        performers[1].setKeyTo(24);
        performers[1].setVelFrom(20);
        performers[1].setVelTo(90);
        performers[1].setSelfNonExclusive(false);
        performers[0].setDefaultConnectionsEnabled(true);
        performers[1].getConnectionBlocks().add(new ModelConnectionBlock());
        performers[1].getOscillators().add(new ModelByteBufferWavetable(new ModelByteBuffer(new byte[] {1,2,3})));

        Patch patch = new Patch(0,36);
        instrument.setPatch(patch);
        assertEquals(instrument.getPatch().getProgram(), patch.getProgram());
        assertEquals(instrument.getPatch().getBank(), patch.getBank());
    }
}
