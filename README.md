[![ASERVO Software GmbH](https://aservo.github.io/img/aservo_atlassian_banner.png)](https://www.aservo.com/en/atlassian)

ConfAPI Commons
===============

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/de.aservo/confapi-commons/badge.svg)](https://maven-badges.herokuapp.com/maven-central/de.aservo/confapi-commons)
[![Build Status](https://github.com/aservo/confapi-commons/actions/workflows/ci_main.yaml/badge.svg)](https://github.com/aservo/confapi-commons/actions/workflows/ci_main.yaml)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=aservo_confapi-commons&metric=coverage)](https://sonarcloud.io/dashboard?id=aservo_confapi-commons)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=aservo_confapi-commons&metric=alert_status)](https://sonarcloud.io/dashboard?id=aservo_confapi-commons)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg?style=flat-square)](http://makeapullrequest.com)

Library for common sources of the ConfAPI plugins:

* [ConfAPI for Jira](https://github.com/aservo/confapi-jira-plugin)
* [ConfAPI for Confluence](https://github.com/aservo/confapi-confluence-plugin)
* [ConfAPI for Bitbucket](https://github.com/aservo/confapi-bitbucket-plugin)
* [ConfAPI for Crowd](https://github.com/aservo/confapi-crowd-plugin)
* [ConfAPI for Fisheye / Crucible](https://github.com/aservo/confapi-fisheye-plugin)

---

## User guide

### What is ConfAPI?

Our ConfAPI Plugin is used to set the attributes:values for several endpoints.
You will find our documentation in the file 'index.adoc'
Inside this file you find a list of all our endpoints and request options.

We will explain some essential things and show you example requests with the endpoint `settings` which you can find in the Index number 2.7.
By clicking on the Index **2.7 Settings** you will get redirected to this section.
Here you can see the method this endpoint implements. We will take a closer look to the **2.7.2. getSettings**

In the first row you can see the path you need to point at to successfully send a request,
followed by a short description what this specific method will do, in our case we will 'get the application settings'.
Also, important information are the content and the return type.
The content type describes how the object will be displayed and the return type tells you what you will get back when you successfully send a request.
For more information about the return type you can click on **SettingsBean** and get a better understanding what this return type contains.

Example:
#### 3.25. SettingsBean
| Field Name              |   Required    | Type    |Description| Format |
|-------------------------|:-------------:|:--------|:---------:|:------:|
| baseUrl                 |               | URI     |           |  uri   |
| mode                    |               | String  |           |        |
| title                   |               | String  |           |        |
| contactMessage          |               | String  |           |        |
| externalUserManagement  |               | Boolean |           |        |

#### **How to send a `GET` request** ?

>**Every** request needs a specific path. If you run Crowd locally for example the 'base' part of the path will be `http://localhost:4990/crowd/rest/confapi/1/`

We want to set up a request for the *2.7.2 getSettings* endpoint, as the method already says, it is the request to get the current settings.
First we take the 'base' path and add the path specified in the endpoint documentation.

>So your *full path* will be this one: `http://localhost:4990/crowd/rest/confapi/1/settings`


Now, lets build and send the request.
>- Set the request method to `GET`
>- Paste the *full path*
>- Hit the 'Send' button
>- Get your result 
>
>```json
>{
>  "baseUrl": "https://example.com",
>  "title": "Example"
>}
>```
>
>(copy your result for the next example)

#### **How to send a `PUT` request**

it is quite similar to update the settings. Now that we know how the object looks like we can rebuild it with our settings easily.

Now, lets update the current settings.
>- Set the request method to `PUT`
>- Paste the *full path* (it's the same path as you can see in the documentation 2.7.2)
>- Paste your result from the `GET` example (or build your own Json object)
>- Edit your values you want to change
>
>```json
>{
>  "baseUrl": "https://myurl.com",
>  "title": "This is my Title!"
>}
>```
>- Hit the 'Send' button
>- Get your result

#### **Do I need to type all attributes everytime?**

No. you can also send a request with only one attribute set. Our plugin is designed to keep all others settings you didn't change.














