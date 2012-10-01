package com.readytalk.gradle.publishers

import org.gradle.api.Task
import org.gradle.api.Project
import org.gradle.api.Plugin

interface PublisherConvention extends Plugin<Project> {
  
  void addInstallTask(Class<Task> task)

  void addPublishTask(Class<Task> task)

}