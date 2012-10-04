package com.readytalk.gradle.plugins

import org.gradle.api.Project
import org.gradle.api.Plugin
import org.gradle.api.tasks.bundling.Jar

import org.gradle.api.DefaultTask

class GroovyExtras implements Plugin<Project> {

  static final String GROOVYDOCJAR_TASK_NAME = 'groovydocJar'

  private Project project

  void apply(Project target) {
    this.project = target

    addGroovydocJarTask()

    project.build.dependsOn(GROOVYDOCJAR_TASK_NAME)

    project.artifacts {
      archives project.groovydocJar
    }
  }

  private void addGroovydocJarTask() {
    project.tasks.add(name: GROOVYDOCJAR_TASK_NAME, type: Jar) {
      classifier = 'groovydoc'
      from project.groovydoc.outputs.files
      group 'documentation'
      description 'Create a Groovy documentation jar'
      dependsOn 'groovydoc'
    }
  }

}