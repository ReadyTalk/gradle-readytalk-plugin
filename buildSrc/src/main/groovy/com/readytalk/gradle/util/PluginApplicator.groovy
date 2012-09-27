package com.readytalk.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project

class PluginApplicator extends Plugin<Project> {
  
  protected Project project

  void apply(Project project) {
    this.project = project
  }

}