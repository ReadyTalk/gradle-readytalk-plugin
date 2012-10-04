package com.readytalk.gradle.plugins

import org.gradle.api.Project
import org.gradle.api.Plugin

import com.readytalk.gradle.extensions.Environment

class ReleaseManager implements Plugin<Project> {

  void apply(Project project) {
    project.extensions.create('release', Environment, project)
  }

}