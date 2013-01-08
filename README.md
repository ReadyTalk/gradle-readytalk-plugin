gradle-readytalk-plugin
=======================

Introduction
------------
There are many goals of this project:

- maintain one place to configure all internal and external gradle builds
- maintain one place of plugin applications (every project uses code quality plugins)
The goal of this plugin is to make it easy to apply project standards to internal and external projects, where internal projects might be published to different looking repository systems (Sonatype vs. Artifactory) than the external versions. Ideally, I'd like to just change one variable to switch the publishing system on a project to convert the project from an internal project to an external one. I

[![Build Status](https://buildhive.cloudbees.com/job/ReadyTalk/job/gradle-readytalk-plugin/badge/icon)](https://buildhive.cloudbees.com/job/ReadyTalk/job/gradle-readytalk-plugin/)

curl -Lu ${USER} https://artifactory.company.com/artifactory/plugins-releases-local/com/readytalk/gradle/init.gradle -o ~/.gradle/init.gradle

