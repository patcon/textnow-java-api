package com.twilio.sdk;


/*
Copyright (c) 2013 Twilio, Inc.

Permission is hereby granted, free of charge, to any person
obtaining a copy of this software and associated documentation
files (the "Software"), to deal in the Software without
restriction, including without limitation the rights to use,
copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the
Software is furnished to do so, subject to the following
conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
OTHER DEALINGS IN THE SOFTWARE.
*/

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class TwilioUtils {

    protected String hardcodedKey = "f8ab2ceca9163724b6d126aea9620339";
    
    public TwilioUtils(){
        this.hardcodedKey = hardcodedKey;
    }

    public boolean validateRequest(String expectedSignature, String method, String url, Map<String,String> params) {
        String signature = null;

        signature = getValidationSignature(method, url, params);
        return secureCompare(signature, expectedSignature);
    }
    
    public String getValidationSignature(String method, String url, Map<String,String> params) {
        String catString = this.hardcodedKey + method + url;
        System.out.println(catString);
        String signature = md5(catString);

        return signature;
    }

    public String md5(String string) {
      try {
          // Create MD5 Hash
          MessageDigest digest = java.security.MessageDigest
                  .getInstance("MD5");
          digest.update(string.getBytes());
          byte messageDigest[] = digest.digest();

          // Create Hex String
          StringBuffer hexString = new StringBuffer();
          for (int i = 0; i < messageDigest.length; i++) {
              String h = Integer.toHexString(0xFF & messageDigest[i]);
              while (h.length() < 2)
                  h = "0" + h;
              hexString.append(h);
          }
          return hexString.toString();

      } catch (NoSuchAlgorithmException e) {
          e.printStackTrace();
      }
      return "";
    }

    /**
     * Securely compare two strings, using constant time to avoid timing
     * attacks.  We can't use MessageDigest.isEqual because it didn't do
     * constant-time compares until JDK6u17 - see:
     * http://codahale.com/a-lesson-in-timing-attacks/
     */
    static boolean secureCompare(String a, String b) {
      if (a == null || b == null) {
        return false;
      }
      int n = a.length();
      if (n != b.length()) {
        return false;
      }
      int mismatch = 0;
      for (int i = 0; i < n; ++i) {
        char chA = a.charAt(i);
        char chB = b.charAt(i);
        mismatch |= chA ^ chB;
      }
      return mismatch == 0;
    }
}
