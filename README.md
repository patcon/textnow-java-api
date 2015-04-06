# TextNowJava

[![Build Status](https://travis-ci.org/patcon/textnow-java-api.png?branch=textnow-port)](https://travis-ci.org/patcon/textnow-java-api)

An **unofficial** Java client library for interacting with the TextNow API.

# Installing

TextNowJava uses Maven.  At present the jars *are* available from a public [maven](http://maven.apache.org/download.html) repository.

Use the following dependency in your project:

       <dependency>
          <groupId>net.nodescription.textnow</groupId>
          <artifactId>textnow-java-api</artifactId>
          <version>0.0.0</version>
          <scope>compile</scope>
       </dependency>

You'll need to compile it yourself, as it's not uploaded to any
repositories. (Tests do not yet run successfully.) Here's how:

    $ git clone git@github.com:patcon/textnow-java-api
    $ cd twilio-java
    $ mvn clean install -DskipTests       # Test do not yet run successfully

Pre-built jars are not yet available.

# Examples

Here are some examples (also found in [TwilioRestExamples.java](https://github.com/twilio/twilio-java/blob/master/src/main/java/com/twilio/sdk/examples/RestExamples.java) )

```java
package net.nodescription.textnow.examples;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.Account;

public class Example {

  public static final String ACCOUNT_SID = "myusername";
  public static final String AUTH_TOKEN = "......."; # for now, must sniff on the wire

  public static void main(final String[] args) throws TwilioRestException {

    // Create a rest client
    final TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);

    // Get the main account (The one we used to authenticate the client)
    final Account account = client.getAccount();

    System.out.println(account.getPhoneNumber());
  }
}
```
