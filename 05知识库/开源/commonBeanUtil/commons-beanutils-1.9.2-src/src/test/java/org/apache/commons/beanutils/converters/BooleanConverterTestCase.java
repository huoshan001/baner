/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.commons.beanutils.converters;

import junit.framework.TestCase;

import org.apache.commons.beanutils.ConversionException;

/**
 * @version $Id: BooleanConverterTestCase.java 1540186 2013-11-08 21:08:30Z oheger $
 */
public class BooleanConverterTestCase extends TestCase {

    public static final String[] STANDARD_TRUES = new String[] {
            "yes", "y", "true", "on", "1"
        };

    public static final String[] STANDARD_FALSES = new String[] {
            "no", "n", "false", "off", "0"
        };


    public BooleanConverterTestCase(String name) {
        super(name);
    }

    public void testStandardValues() {
        BooleanConverter converter = new BooleanConverter();
        testConversionValues(converter, STANDARD_TRUES, STANDARD_FALSES);
    }

    public void testCaseInsensitivity() {
        BooleanConverter converter = new BooleanConverter();
        testConversionValues(
            converter,
            new String[] {"Yes", "TRUE"},
            new String[] {"NO", "fAlSe"});
    }


    public void testInvalidString() {
        BooleanConverter converter = new BooleanConverter();
        try {
            converter.convert(Boolean.class, "bogus");
            fail("Converting invalid string should have generated an exception");
        } catch (ConversionException expected) {
            // Exception is successful test
        }
    }

    public void testDefaultValue() {
        Object defaultValue = Boolean.TRUE;
        BooleanConverter converter = new BooleanConverter(defaultValue);

        assertSame(defaultValue, converter.convert(Boolean.class, "bogus"));
        testConversionValues(converter, STANDARD_TRUES, STANDARD_FALSES);
    }

    public void testAdditionalStrings() {
        String[] trueStrings = {"sure"};
        String[] falseStrings = {"nope"};
        BooleanConverter converter = new BooleanConverter(
            trueStrings, falseStrings, BooleanConverter.NO_DEFAULT);
        testConversionValues(
            converter,
            new String[] {"sure", "Sure"},
            new String[] {"nope", "nOpE"});

        try {
            converter.convert(Boolean.class, "true");
            fail("Converting obsolete true value should have generated an exception");
        } catch (ConversionException expected) {
            // Exception is successful test
        }
        try {
            converter.convert(Boolean.class, "bogus");
            fail("Converting invalid string should have generated an exception");
        } catch (ConversionException expected) {
            // Exception is successful test
        }
    }

    /**
     * Tests a conversion to another target type. This should not be possible.
     */
    public void testConversionToOtherType() {
        BooleanConverter converter = new BooleanConverter();
        try {
            converter.convert(Integer.class, STANDARD_TRUES[0]);
            fail("Could convert to unsupported type!");
        } catch (ConversionException cex) {
            // Expected result
        }
    }

    /**
     * Tests whether a conversion to a primitive boolean is possible.
     */
    public void testPrimitiveTargetClass() {
        BooleanConverter converter = new BooleanConverter();
        assertTrue("Wrong result", converter.convert(Boolean.TYPE, STANDARD_TRUES[0]));
    }

    protected void testConversionValues(BooleanConverter converter,
            String[] trueValues, String[] falseValues) {

        for (int i = 0; i < trueValues.length; i++) {
            assertEquals(Boolean.TRUE, converter.convert(Boolean.class, trueValues[i]));
        }
        for (int i = 0; i < falseValues.length; i++) {
            assertEquals(Boolean.FALSE, converter.convert(Boolean.class, falseValues[i]));
        }
    }

}
