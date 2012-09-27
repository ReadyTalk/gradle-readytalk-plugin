package com.readytalk.gradle.publishers

interface PublisherConvention {
  
  void addInstallTask(Class<Task> task)

  void addPublishTask(Class<Task> task)

}