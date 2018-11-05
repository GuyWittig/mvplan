/*
 * PlatformDetector.java
 *
 *   Used to detect operating system.
 *
 *   @author Guy Wittig
 *   @version 18-Jun-2006
 *
 *   This program is part of MV-Plan
 *   Copywrite 2006 Guy Wittig
 *
 *   This program is free software; you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation; either version 2 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   The GNU General Public License can be read at http://www.gnu.org/licenses/licenses.html
 *
 */

package mvplan.util;

/**
 *
 * @author guy
 */
public class PlatformDetector {
    public static final int MACOS=0;
    public static final int UNIX=1;
    public static final int WINDOWS=3;
        
    /**
     * Detects platform
     * @return Platform identifier
     *
     */
    public static int detect() {
         String osName = System.getProperty("os.name");
         if (osName.startsWith("Mac OS"))
             return MACOS;
         else if (osName.startsWith("Windows")) 
             return WINDOWS;
         else  //assume Unix or Linux
             return UNIX;
    }    
}
