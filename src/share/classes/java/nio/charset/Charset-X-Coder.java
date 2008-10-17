/*
 * Copyright 2000-2008 Sun Microsystems, Inc.  All Rights Reserved.
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

#warn This file is preprocessed before being compiled

package java.nio.charset;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.lang.ref.WeakReference;
import java.nio.charset.CoderMalfunctionError;                  // javadoc


/**
 * An engine that can transform a sequence of $itypesPhrase$ into a sequence of
 * $otypesPhrase$.
 *
 * <a name="steps">
 *
 * <p> The input $itype$ sequence is provided in a $itype$ buffer or a series
 * of such buffers.  The output $otype$ sequence is written to a $otype$ buffer
 * or a series of such buffers.  $A$ $coder$ should always be used by making
 * the following sequence of method invocations, hereinafter referred to as $a$
 * <i>$coding$ operation</i>:
 *
 * <ol>
 *
 *   <li><p> Reset the $coder$ via the {@link #reset reset} method, unless it
 *   has not been used before; </p></li>
 *
 *   <li><p> Invoke the {@link #$code$ $code$} method zero or more times, as
 *   long as additional input may be available, passing <tt>false</tt> for the
 *   <tt>endOfInput</tt> argument and filling the input buffer and flushing the
 *   output buffer between invocations; </p></li>
 *
 *   <li><p> Invoke the {@link #$code$ $code$} method one final time, passing
 *   <tt>true</tt> for the <tt>endOfInput</tt> argument; and then </p></li>
 *
 *   <li><p> Invoke the {@link #flush flush} method so that the $coder$ can
 *   flush any internal state to the output buffer. </p></li>
 *
 * </ol>
 *
 * Each invocation of the {@link #$code$ $code$} method will $code$ as many
 * $itype$s as possible from the input buffer, writing the resulting $otype$s
 * to the output buffer.  The {@link #$code$ $code$} method returns when more
 * input is required, when there is not enough room in the output buffer, or
 * when $a$ $coding$ error has occurred.  In each case a {@link CoderResult}
 * object is returned to describe the reason for termination.  An invoker can
 * examine this object and fill the input buffer, flush the output buffer, or
 * attempt to recover from $a$ $coding$ error, as appropriate, and try again.
 *
 * <a name="ce">
 *
 * <p> There are two general types of $coding$ errors.  If the input $itype$
 * sequence is $notLegal$ then the input is considered <i>malformed</i>.  If
 * the input $itype$ sequence is legal but cannot be mapped to a valid
 * $outSequence$ then an <i>unmappable character</i> has been encountered.
 *
 * <a name="cae">
 *
 * <p> How $a$ $coding$ error is handled depends upon the action requested for
 * that type of error, which is described by an instance of the {@link
 * CodingErrorAction} class.  The possible error actions are to {@link
 * CodingErrorAction#IGNORE </code>ignore<code>} the erroneous input, {@link
 * CodingErrorAction#REPORT </code>report<code>} the error to the invoker via
 * the returned {@link CoderResult} object, or {@link CodingErrorAction#REPLACE
 * </code>replace<code>} the erroneous input with the current value of the
 * replacement $replTypeName$.  The replacement
 *
#if[encoder]
 * is initially set to the $coder$'s default replacement, which often
 * (but not always) has the initial value&nbsp;$defaultReplName$;
#end[encoder]
#if[decoder]
 * has the initial value $defaultReplName$;
#end[decoder]
 *
 * its value may be changed via the {@link #replaceWith($replFQType$)
 * replaceWith} method.
 *
 * <p> The default action for malformed-input and unmappable-character errors
 * is to {@link CodingErrorAction#REPORT </code>report<code>} them.  The
 * malformed-input error action may be changed via the {@link
 * #onMalformedInput(CodingErrorAction) onMalformedInput} method; the
 * unmappable-character action may be changed via the {@link
 * #onUnmappableCharacter(CodingErrorAction) onUnmappableCharacter} method.
 *
 * <p> This class is designed to handle many of the details of the $coding$
 * process, including the implementation of error actions.  $A$ $coder$ for a
 * specific charset, which is a concrete subclass of this class, need only
 * implement the abstract {@link #$code$Loop $code$Loop} method, which
 * encapsulates the basic $coding$ loop.  A subclass that maintains internal
 * state should, additionally, override the {@link #implFlush implFlush} and
 * {@link #implReset implReset} methods.
 *
 * <p> Instances of this class are not safe for use by multiple concurrent
 * threads.  </p>
 *
 *
 * @author Mark Reinhold
 * @author JSR-51 Expert Group
 * @since 1.4
 *
 * @see ByteBuffer
 * @see CharBuffer
 * @see Charset
 * @see Charset$OtherCoder$
 */

public abstract class Charset$Coder$ {

    private final Charset charset;
    private final float average$ItypesPerOtype$;
    private final float max$ItypesPerOtype$;

    private $replType$ replacement;
    private CodingErrorAction malformedInputAction
        = CodingErrorAction.REPORT;
    private CodingErrorAction unmappableCharacterAction
        = CodingErrorAction.REPORT;

    // Internal states
    //
    private static final int ST_RESET   = 0;
    private static final int ST_CODING  = 1;
    private static final int ST_END     = 2;
    private static final int ST_FLUSHED = 3;

    private int state = ST_RESET;

    private static String stateNames[]
        = { "RESET", "CODING", "CODING_END", "FLUSHED" };


    /**
     * Initializes a new $coder$.  The new $coder$ will have the given
     * $otypes-per-itype$ and replacement values. </p>
     *
     * @param  average$ItypesPerOtype$
     *         A positive float value indicating the expected number of
     *         $otype$s that will be produced for each input $itype$
     *
     * @param  max$ItypesPerOtype$
     *         A positive float value indicating the maximum number of
     *         $otype$s that will be produced for each input $itype$
     *
     * @param  replacement
     *         The initial replacement; must not be <tt>null</tt>, must have
     *         non-zero length, must not be longer than max$ItypesPerOtype$,
     *         and must be {@link #isLegalReplacement </code>legal<code>}
     *
     * @throws  IllegalArgumentException
     *          If the preconditions on the parameters do not hold
     */
    {#if[encoder]?protected:private}
    Charset$Coder$(Charset cs,
                   float average$ItypesPerOtype$,
                   float max$ItypesPerOtype$,
                   $replType$ replacement)
    {
        this.charset = cs;
        if (average$ItypesPerOtype$ <= 0.0f)
            throw new IllegalArgumentException("Non-positive "
                                               + "average$ItypesPerOtype$");
        if (max$ItypesPerOtype$ <= 0.0f)
            throw new IllegalArgumentException("Non-positive "
                                               + "max$ItypesPerOtype$");
        if (!Charset.atBugLevel("1.4")) {
            if (average$ItypesPerOtype$ > max$ItypesPerOtype$)
                throw new IllegalArgumentException("average$ItypesPerOtype$"
                                                   + " exceeds "
                                                   + "max$ItypesPerOtype$");
        }
        this.replacement = replacement;
        this.average$ItypesPerOtype$ = average$ItypesPerOtype$;
        this.max$ItypesPerOtype$ = max$ItypesPerOtype$;
        replaceWith(replacement);
    }

    /**
     * Initializes a new $coder$.  The new $coder$ will have the given
     * $otypes-per-itype$ values and its replacement will be the
     * $replTypeName$ $defaultReplName$. </p>
     *
     * @param  average$ItypesPerOtype$
     *         A positive float value indicating the expected number of
     *         $otype$s that will be produced for each input $itype$
     *
     * @param  max$ItypesPerOtype$
     *         A positive float value indicating the maximum number of
     *         $otype$s that will be produced for each input $itype$
     *
     * @throws  IllegalArgumentException
     *          If the preconditions on the parameters do not hold
     */
    protected Charset$Coder$(Charset cs,
                             float average$ItypesPerOtype$,
                             float max$ItypesPerOtype$)
    {
        this(cs,
             average$ItypesPerOtype$, max$ItypesPerOtype$,
             $defaultRepl$);
    }

    /**
     * Returns the charset that created this $coder$.  </p>
     *
     * @return  This $coder$'s charset
     */
    public final Charset charset() {
        return charset;
    }

    /**
     * Returns this $coder$'s replacement value. </p>
     *
     * @return  This $coder$'s current replacement,
     *          which is never <tt>null</tt> and is never empty
     */
    public final $replType$ replacement() {
        return replacement;
    }

    /**
     * Changes this $coder$'s replacement value.
     *
     * <p> This method invokes the {@link #implReplaceWith implReplaceWith}
     * method, passing the new replacement, after checking that the new
     * replacement is acceptable.  </p>
     *
     * @param  newReplacement
     *
#if[decoder]
     *         The new replacement; must not be <tt>null</tt>
     *         and must have non-zero length
#end[decoder]
#if[encoder]
     *         The new replacement; must not be <tt>null</tt>, must have
     *         non-zero length, must not be longer than the value returned by
     *         the {@link #max$ItypesPerOtype$() max$ItypesPerOtype$} method, and
     *         must be {@link #isLegalReplacement </code>legal<code>}
#end[encoder]
     *
     * @return  This $coder$
     *
     * @throws  IllegalArgumentException
     *          If the preconditions on the parameter do not hold
     */
    public final Charset$Coder$ replaceWith($replType$ newReplacement) {
        if (newReplacement == null)
            throw new IllegalArgumentException("Null replacement");
        int len = newReplacement.$replLength$;
        if (len == 0)
            throw new IllegalArgumentException("Empty replacement");
        if (len > max$ItypesPerOtype$)
            throw new IllegalArgumentException("Replacement too long");
#if[encoder]
        if (!isLegalReplacement(newReplacement))
            throw new IllegalArgumentException("Illegal replacement");
#end[encoder]
        this.replacement = newReplacement;
        implReplaceWith(newReplacement);
        return this;
    }

    /**
     * Reports a change to this $coder$'s replacement value.
     *
     * <p> The default implementation of this method does nothing.  This method
     * should be overridden by $coder$s that require notification of changes to
     * the replacement.  </p>
     *
     * @param  newReplacement
     */
    protected void implReplaceWith($replType$ newReplacement) {
    }

#if[encoder]

    private WeakReference<CharsetDecoder> cachedDecoder = null;

    /**
     * Tells whether or not the given byte array is a legal replacement value
     * for this encoder.
     *
     * <p> A replacement is legal if, and only if, it is a legal sequence of
     * bytes in this encoder's charset; that is, it must be possible to decode
     * the replacement into one or more sixteen-bit Unicode characters.
     *
     * <p> The default implementation of this method is not very efficient; it
     * should generally be overridden to improve performance.  </p>
     *
     * @param  repl  The byte array to be tested
     *
     * @return  <tt>true</tt> if, and only if, the given byte array
     *          is a legal replacement value for this encoder
     */
    public boolean isLegalReplacement(byte[] repl) {
        WeakReference<CharsetDecoder> wr = cachedDecoder;
        CharsetDecoder dec = null;
        if ((wr == null) || ((dec = wr.get()) == null)) {
            dec = charset().newDecoder();
            dec.onMalformedInput(CodingErrorAction.REPORT);
            dec.onUnmappableCharacter(CodingErrorAction.REPORT);
            cachedDecoder = new WeakReference<CharsetDecoder>(dec);
        } else {
            dec.reset();
        }
        ByteBuffer bb = ByteBuffer.wrap(repl);
        CharBuffer cb = CharBuffer.allocate((int)(bb.remaining()
                                                  * dec.maxCharsPerByte()));
        CoderResult cr = dec.decode(bb, cb, true);
        return !cr.isError();
    }

#end[encoder]

    /**
     * Returns this $coder$'s current action for malformed-input errors.  </p>
     *
     * @return The current malformed-input action, which is never <tt>null</tt>
     */
    public CodingErrorAction malformedInputAction() {
        return malformedInputAction;
    }

    /**
     * Changes this $coder$'s action for malformed-input errors.  </p>
     *
     * <p> This method invokes the {@link #implOnMalformedInput
     * implOnMalformedInput} method, passing the new action.  </p>
     *
     * @param  newAction  The new action; must not be <tt>null</tt>
     *
     * @return  This $coder$
     *
     * @throws IllegalArgumentException
     *         If the precondition on the parameter does not hold
     */
    public final Charset$Coder$ onMalformedInput(CodingErrorAction newAction) {
        if (newAction == null)
            throw new IllegalArgumentException("Null action");
        malformedInputAction = newAction;
        implOnMalformedInput(newAction);
        return this;
    }

    /**
     * Reports a change to this $coder$'s malformed-input action.
     *
     * <p> The default implementation of this method does nothing.  This method
     * should be overridden by $coder$s that require notification of changes to
     * the malformed-input action.  </p>
     */
    protected void implOnMalformedInput(CodingErrorAction newAction) { }

    /**
     * Returns this $coder$'s current action for unmappable-character errors.
     * </p>
     *
     * @return The current unmappable-character action, which is never
     *         <tt>null</tt>
     */
    public CodingErrorAction unmappableCharacterAction() {
        return unmappableCharacterAction;
    }

    /**
     * Changes this $coder$'s action for unmappable-character errors.
     *
     * <p> This method invokes the {@link #implOnUnmappableCharacter
     * implOnUnmappableCharacter} method, passing the new action.  </p>
     *
     * @param  newAction  The new action; must not be <tt>null</tt>
     *
     * @return  This $coder$
     *
     * @throws IllegalArgumentException
     *         If the precondition on the parameter does not hold
     */
    public final Charset$Coder$ onUnmappableCharacter(CodingErrorAction
                                                      newAction)
    {
        if (newAction == null)
            throw new IllegalArgumentException("Null action");
        unmappableCharacterAction = newAction;
        implOnUnmappableCharacter(newAction);
        return this;
    }

    /**
     * Reports a change to this $coder$'s unmappable-character action.
     *
     * <p> The default implementation of this method does nothing.  This method
     * should be overridden by $coder$s that require notification of changes to
     * the unmappable-character action.  </p>
     */
    protected void implOnUnmappableCharacter(CodingErrorAction newAction) { }

    /**
     * Returns the average number of $otype$s that will be produced for each
     * $itype$ of input.  This heuristic value may be used to estimate the size
     * of the output buffer required for a given input sequence. </p>
     *
     * @return  The average number of $otype$s produced
     *          per $itype$ of input
     */
    public final float average$ItypesPerOtype$() {
        return average$ItypesPerOtype$;
    }

    /**
     * Returns the maximum number of $otype$s that will be produced for each
     * $itype$ of input.  This value may be used to compute the worst-case size
     * of the output buffer required for a given input sequence. </p>
     *
     * @return  The maximum number of $otype$s that will be produced per
     *          $itype$ of input
     */
    public final float max$ItypesPerOtype$() {
        return max$ItypesPerOtype$;
    }

    /**
     * $Code$s as many $itype$s as possible from the given input buffer,
     * writing the results to the given output buffer.
     *
     * <p> The buffers are read from, and written to, starting at their current
     * positions.  At most {@link Buffer#remaining in.remaining()} $itype$s
     * will be read and at most {@link Buffer#remaining out.remaining()}
     * $otype$s will be written.  The buffers' positions will be advanced to
     * reflect the $itype$s read and the $otype$s written, but their marks and
     * limits will not be modified.
     *
     * <p> In addition to reading $itype$s from the input buffer and writing
     * $otype$s to the output buffer, this method returns a {@link CoderResult}
     * object to describe its reason for termination:
     *
     * <ul>
     *
     *   <li><p> {@link CoderResult#UNDERFLOW} indicates that as much of the
     *   input buffer as possible has been $code$d.  If there is no further
     *   input then the invoker can proceed to the next step of the
     *   <a href="#steps">$coding$ operation</a>.  Otherwise this method
     *   should be invoked again with further input.  </p></li>
     *
     *   <li><p> {@link CoderResult#OVERFLOW} indicates that there is
     *   insufficient space in the output buffer to $code$ any more $itype$s.
     *   This method should be invoked again with an output buffer that has
     *   more {@linkplain Buffer#remaining remaining} $otype$s. This is
     *   typically done by draining any $code$d $otype$s from the output
     *   buffer.  </p></li>
     *
     *   <li><p> A {@link CoderResult#malformedForLength
     *   </code>malformed-input<code>} result indicates that a malformed-input
     *   error has been detected.  The malformed $itype$s begin at the input
     *   buffer's (possibly incremented) position; the number of malformed
     *   $itype$s may be determined by invoking the result object's {@link
     *   CoderResult#length() length} method.  This case applies only if the
     *   {@link #onMalformedInput </code>malformed action<code>} of this $coder$
     *   is {@link CodingErrorAction#REPORT}; otherwise the malformed input
     *   will be ignored or replaced, as requested.  </p></li>
     *
     *   <li><p> An {@link CoderResult#unmappableForLength
     *   </code>unmappable-character<code>} result indicates that an
     *   unmappable-character error has been detected.  The $itype$s that
     *   $code$ the unmappable character begin at the input buffer's (possibly
     *   incremented) position; the number of such $itype$s may be determined
     *   by invoking the result object's {@link CoderResult#length() length}
     *   method.  This case applies only if the {@link #onUnmappableCharacter
     *   </code>unmappable action<code>} of this $coder$ is {@link
     *   CodingErrorAction#REPORT}; otherwise the unmappable character will be
     *   ignored or replaced, as requested.  </p></li>
     *
     * </ul>
     *
     * In any case, if this method is to be reinvoked in the same $coding$
     * operation then care should be taken to preserve any $itype$s remaining
     * in the input buffer so that they are available to the next invocation.
     *
     * <p> The <tt>endOfInput</tt> parameter advises this method as to whether
     * the invoker can provide further input beyond that contained in the given
     * input buffer.  If there is a possibility of providing additional input
     * then the invoker should pass <tt>false</tt> for this parameter; if there
     * is no possibility of providing further input then the invoker should
     * pass <tt>true</tt>.  It is not erroneous, and in fact it is quite
     * common, to pass <tt>false</tt> in one invocation and later discover that
     * no further input was actually available.  It is critical, however, that
     * the final invocation of this method in a sequence of invocations always
     * pass <tt>true</tt> so that any remaining un$code$d input will be treated
     * as being malformed.
     *
     * <p> This method works by invoking the {@link #$code$Loop $code$Loop}
     * method, interpreting its results, handling error conditions, and
     * reinvoking it as necessary.  </p>
     *
     *
     * @param  in
     *         The input $itype$ buffer
     *
     * @param  out
     *         The output $otype$ buffer
     *
     * @param  endOfInput
     *         <tt>true</tt> if, and only if, the invoker can provide no
     *         additional input $itype$s beyond those in the given buffer
     *
     * @return  A coder-result object describing the reason for termination
     *
     * @throws  IllegalStateException
     *          If $a$ $coding$ operation is already in progress and the previous
     *          step was an invocation neither of the {@link #reset reset}
     *          method, nor of this method with a value of <tt>false</tt> for
     *          the <tt>endOfInput</tt> parameter, nor of this method with a
     *          value of <tt>true</tt> for the <tt>endOfInput</tt> parameter
     *          but a return value indicating an incomplete $coding$ operation
     *
     * @throws  CoderMalfunctionError
     *          If an invocation of the $code$Loop method threw
     *          an unexpected exception
     */
    public final CoderResult $code$($Itype$Buffer in, $Otype$Buffer out,
                                    boolean endOfInput)
    {
        int newState = endOfInput ? ST_END : ST_CODING;
        if ((state != ST_RESET) && (state != ST_CODING)
            && !(endOfInput && (state == ST_END)))
            throwIllegalStateException(state, newState);
        state = newState;

        for (;;) {

            CoderResult cr;
            try {
                cr = $code$Loop(in, out);
            } catch (BufferUnderflowException x) {
                throw new CoderMalfunctionError(x);
            } catch (BufferOverflowException x) {
                throw new CoderMalfunctionError(x);
            }

            if (cr.isOverflow())
                return cr;

            if (cr.isUnderflow()) {
                if (endOfInput && in.hasRemaining()) {
                    cr = CoderResult.malformedForLength(in.remaining());
                    // Fall through to malformed-input case
                } else {
                    return cr;
                }
            }

            CodingErrorAction action = null;
            if (cr.isMalformed())
                action = malformedInputAction;
            else if (cr.isUnmappable())
                action = unmappableCharacterAction;
            else
                assert false : cr.toString();

            if (action == CodingErrorAction.REPORT)
                return cr;

            if (action == CodingErrorAction.REPLACE) {
                if (out.remaining() < replacement.$replLength$)
                    return CoderResult.OVERFLOW;
                out.put(replacement);
            }

            if ((action == CodingErrorAction.IGNORE)
                || (action == CodingErrorAction.REPLACE)) {
                // Skip erroneous input either way
                in.position(in.position() + cr.length());
                continue;
            }

            assert false;
        }

    }

    /**
     * Flushes this $coder$.
     *
     * <p> Some $coder$s maintain internal state and may need to write some
     * final $otype$s to the output buffer once the overall input sequence has
     * been read.
     *
     * <p> Any additional output is written to the output buffer beginning at
     * its current position.  At most {@link Buffer#remaining out.remaining()}
     * $otype$s will be written.  The buffer's position will be advanced
     * appropriately, but its mark and limit will not be modified.
     *
     * <p> If this method completes successfully then it returns {@link
     * CoderResult#UNDERFLOW}.  If there is insufficient room in the output
     * buffer then it returns {@link CoderResult#OVERFLOW}.  If this happens
     * then this method must be invoked again, with an output buffer that has
     * more room, in order to complete the current <a href="#steps">$coding$
     * operation</a>.
     *
     * <p> If this $coder$ has already been flushed then invoking this method
     * has no effect.
     *
     * <p> This method invokes the {@link #implFlush implFlush} method to
     * perform the actual flushing operation.  </p>
     *
     * @param  out
     *         The output $otype$ buffer
     *
     * @return  A coder-result object, either {@link CoderResult#UNDERFLOW} or
     *          {@link CoderResult#OVERFLOW}
     *
     * @throws  IllegalStateException
     *          If the previous step of the current $coding$ operation was an
     *          invocation neither of the {@link #flush flush} method nor of
     *          the three-argument {@link
     *          #$code$($Itype$Buffer,$Otype$Buffer,boolean) $code$} method
     *          with a value of <tt>true</tt> for the <tt>endOfInput</tt>
     *          parameter
     */
    public final CoderResult flush($Otype$Buffer out) {
        if (state == ST_END) {
            CoderResult cr = implFlush(out);
            if (cr.isUnderflow())
                state = ST_FLUSHED;
            return cr;
        }

        if (state != ST_FLUSHED)
            throwIllegalStateException(state, ST_FLUSHED);

        return CoderResult.UNDERFLOW; // Already flushed
    }

    /**
     * Flushes this $coder$.
     *
     * <p> The default implementation of this method does nothing, and always
     * returns {@link CoderResult#UNDERFLOW}.  This method should be overridden
     * by $coder$s that may need to write final $otype$s to the output buffer
     * once the entire input sequence has been read. </p>
     *
     * @param  out
     *         The output $otype$ buffer
     *
     * @return  A coder-result object, either {@link CoderResult#UNDERFLOW} or
     *          {@link CoderResult#OVERFLOW}
     */
    protected CoderResult implFlush($Otype$Buffer out) {
        return CoderResult.UNDERFLOW;
    }

    /**
     * Resets this $coder$, clearing any internal state.
     *
     * <p> This method resets charset-independent state and also invokes the
     * {@link #implReset() implReset} method in order to perform any
     * charset-specific reset actions.  </p>
     *
     * @return  This $coder$
     *
     */
    public final Charset$Coder$ reset() {
        implReset();
        state = ST_RESET;
        return this;
    }

    /**
     * Resets this $coder$, clearing any charset-specific internal state.
     *
     * <p> The default implementation of this method does nothing.  This method
     * should be overridden by $coder$s that maintain internal state.  </p>
     */
    protected void implReset() { }

    /**
     * $Code$s one or more $itype$s into one or more $otype$s.
     *
     * <p> This method encapsulates the basic $coding$ loop, $coding$ as many
     * $itype$s as possible until it either runs out of input, runs out of room
     * in the output buffer, or encounters $a$ $coding$ error.  This method is
     * invoked by the {@link #$code$ $code$} method, which handles result
     * interpretation and error recovery.
     *
     * <p> The buffers are read from, and written to, starting at their current
     * positions.  At most {@link Buffer#remaining in.remaining()} $itype$s
     * will be read, and at most {@link Buffer#remaining out.remaining()}
     * $otype$s will be written.  The buffers' positions will be advanced to
     * reflect the $itype$s read and the $otype$s written, but their marks and
     * limits will not be modified.
     *
     * <p> This method returns a {@link CoderResult} object to describe its
     * reason for termination, in the same manner as the {@link #$code$ $code$}
     * method.  Most implementations of this method will handle $coding$ errors
     * by returning an appropriate result object for interpretation by the
     * {@link #$code$ $code$} method.  An optimized implementation may instead
     * examine the relevant error action and implement that action itself.
     *
     * <p> An implementation of this method may perform arbitrary lookahead by
     * returning {@link CoderResult#UNDERFLOW} until it receives sufficient
     * input.  </p>
     *
     * @param  in
     *         The input $itype$ buffer
     *
     * @param  out
     *         The output $otype$ buffer
     *
     * @return  A coder-result object describing the reason for termination
     */
    protected abstract CoderResult $code$Loop($Itype$Buffer in,
                                              $Otype$Buffer out);

    /**
     * Convenience method that $code$s the remaining content of a single input
     * $itype$ buffer into a newly-allocated $otype$ buffer.
     *
     * <p> This method implements an entire <a href="#steps">$coding$
     * operation</a>; that is, it resets this $coder$, then it $code$s the
     * $itype$s in the given $itype$ buffer, and finally it flushes this
     * $coder$.  This method should therefore not be invoked if $a$ $coding$
     * operation is already in progress.  </p>
     *
     * @param  in
     *         The input $itype$ buffer
     *
     * @return A newly-allocated $otype$ buffer containing the result of the
     *         $coding$ operation.  The buffer's position will be zero and its
     *         limit will follow the last $otype$ written.
     *
     * @throws  IllegalStateException
     *          If $a$ $coding$ operation is already in progress
     *
     * @throws  MalformedInputException
     *          If the $itype$ sequence starting at the input buffer's current
     *          position is $notLegal$ and the current malformed-input action
     *          is {@link CodingErrorAction#REPORT}
     *
     * @throws  UnmappableCharacterException
     *          If the $itype$ sequence starting at the input buffer's current
     *          position cannot be mapped to an equivalent $otype$ sequence and
     *          the current unmappable-character action is {@link
     *          CodingErrorAction#REPORT}
     */
    public final $Otype$Buffer $code$($Itype$Buffer in)
        throws CharacterCodingException
    {
        int n = (int)(in.remaining() * average$ItypesPerOtype$());
        $Otype$Buffer out = $Otype$Buffer.allocate(n);

        if ((n == 0) && (in.remaining() == 0))
            return out;
        reset();
        for (;;) {
            CoderResult cr = in.hasRemaining() ?
                $code$(in, out, true) : CoderResult.UNDERFLOW;
            if (cr.isUnderflow())
                cr = flush(out);

            if (cr.isUnderflow())
                break;
            if (cr.isOverflow()) {
                n = 2*n + 1;    // Ensure progress; n might be 0!
                $Otype$Buffer o = $Otype$Buffer.allocate(n);
                out.flip();
                o.put(out);
                out = o;
                continue;
            }
            cr.throwException();
        }
        out.flip();
        return out;
    }

#if[decoder]

    /**
     * Tells whether or not this decoder implements an auto-detecting charset.
     *
     * <p> The default implementation of this method always returns
     * <tt>false</tt>; it should be overridden by auto-detecting decoders to
     * return <tt>true</tt>.  </p>
     *
     * @return  <tt>true</tt> if, and only if, this decoder implements an
     *          auto-detecting charset
     */
    public boolean isAutoDetecting() {
        return false;
    }

    /**
     * Tells whether or not this decoder has yet detected a
     * charset&nbsp;&nbsp;<i>(optional operation)</i>.
     *
     * <p> If this decoder implements an auto-detecting charset then at a
     * single point during a decoding operation this method may start returning
     * <tt>true</tt> to indicate that a specific charset has been detected in
     * the input byte sequence.  Once this occurs, the {@link #detectedCharset
     * detectedCharset} method may be invoked to retrieve the detected charset.
     *
     * <p> That this method returns <tt>false</tt> does not imply that no bytes
     * have yet been decoded.  Some auto-detecting decoders are capable of
     * decoding some, or even all, of an input byte sequence without fixing on
     * a particular charset.
     *
     * <p> The default implementation of this method always throws an {@link
     * UnsupportedOperationException}; it should be overridden by
     * auto-detecting decoders to return <tt>true</tt> once the input charset
     * has been determined.  </p>
     *
     * @return  <tt>true</tt> if, and only if, this decoder has detected a
     *          specific charset
     *
     * @throws  UnsupportedOperationException
     *          If this decoder does not implement an auto-detecting charset
     */
    public boolean isCharsetDetected() {
        throw new UnsupportedOperationException();
    }

    /**
     * Retrieves the charset that was detected by this
     * decoder&nbsp;&nbsp;<i>(optional operation)</i>.
     *
     * <p> If this decoder implements an auto-detecting charset then this
     * method returns the actual charset once it has been detected.  After that
     * point, this method returns the same value for the duration of the
     * current decoding operation.  If not enough input bytes have yet been
     * read to determine the actual charset then this method throws an {@link
     * IllegalStateException}.
     *
     * <p> The default implementation of this method always throws an {@link
     * UnsupportedOperationException}; it should be overridden by
     * auto-detecting decoders to return the appropriate value.  </p>
     *
     * @return  The charset detected by this auto-detecting decoder,
     *          or <tt>null</tt> if the charset has not yet been determined
     *
     * @throws  IllegalStateException
     *          If insufficient bytes have been read to determine a charset
     *
     * @throws  UnsupportedOperationException
     *          If this decoder does not implement an auto-detecting charset
     */
    public Charset detectedCharset() {
        throw new UnsupportedOperationException();
    }

#end[decoder]

#if[encoder]

    private boolean canEncode(CharBuffer cb) {
        if (state == ST_FLUSHED)
            reset();
        else if (state != ST_RESET)
            throwIllegalStateException(state, ST_CODING);
        CodingErrorAction ma = malformedInputAction();
        CodingErrorAction ua = unmappableCharacterAction();
        try {
            onMalformedInput(CodingErrorAction.REPORT);
            onUnmappableCharacter(CodingErrorAction.REPORT);
            encode(cb);
        } catch (CharacterCodingException x) {
            return false;
        } finally {
            onMalformedInput(ma);
            onUnmappableCharacter(ua);
            reset();
        }
        return true;
    }

    /**
     * Tells whether or not this encoder can encode the given character.
     *
     * <p> This method returns <tt>false</tt> if the given character is a
     * surrogate character; such characters can be interpreted only when they
     * are members of a pair consisting of a high surrogate followed by a low
     * surrogate.  The {@link #canEncode(java.lang.CharSequence)
     * canEncode(CharSequence)} method may be used to test whether or not a
     * character sequence can be encoded.
     *
     * <p> This method may modify this encoder's state; it should therefore not
     * be invoked if an <a href="#steps">encoding operation</a> is already in
     * progress.
     *
     * <p> The default implementation of this method is not very efficient; it
     * should generally be overridden to improve performance.  </p>
     *
     * @return  <tt>true</tt> if, and only if, this encoder can encode
     *          the given character
     *
     * @throws  IllegalStateException
     *          If $a$ $coding$ operation is already in progress
     */
    public boolean canEncode(char c) {
        CharBuffer cb = CharBuffer.allocate(1);
        cb.put(c);
        cb.flip();
        return canEncode(cb);
    }

    /**
     * Tells whether or not this encoder can encode the given character
     * sequence.
     *
     * <p> If this method returns <tt>false</tt> for a particular character
     * sequence then more information about why the sequence cannot be encoded
     * may be obtained by performing a full <a href="#steps">encoding
     * operation</a>.
     *
     * <p> This method may modify this encoder's state; it should therefore not
     * be invoked if an encoding operation is already in progress.
     *
     * <p> The default implementation of this method is not very efficient; it
     * should generally be overridden to improve performance.  </p>
     *
     * @return  <tt>true</tt> if, and only if, this encoder can encode
     *          the given character without throwing any exceptions and without
     *          performing any replacements
     *
     * @throws  IllegalStateException
     *          If $a$ $coding$ operation is already in progress
     */
    public boolean canEncode(CharSequence cs) {
        CharBuffer cb;
        if (cs instanceof CharBuffer)
            cb = ((CharBuffer)cs).duplicate();
        else
            cb = CharBuffer.wrap(cs.toString());
        return canEncode(cb);
    }

#end[encoder]


    private void throwIllegalStateException(int from, int to) {
        throw new IllegalStateException("Current state = " + stateNames[from]
                                        + ", new state = " + stateNames[to]);
    }

}
