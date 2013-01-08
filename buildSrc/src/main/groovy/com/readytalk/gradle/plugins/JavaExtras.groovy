package com.readytalk.gradle.plugins

import org.gradle.api.Project
import org.gradle.api.Plugin
import org.gradle.api.tasks.bundling.Jar
import org.gradle.api.InvalidUserDataException

import org.gradle.api.DefaultTask

class JavaExtrasPlugin implements Plugin<Project> {

  static final String GROOVYDOCJAR_TASK_NAME = 'groovydocJar'
  static final String JAVADOCJAR_TASK_NAME = 'javadocJar'
  static final String SOURCESJAR_TASK_NAME = 'sourcesJar'

  private Project project

  void apply(Project project) {
    this.project = project

    checkForJavaPlugin()

    addJavadocJarTask()
    addSourceJarTask()

    project.build.dependsOn(JAVADOCJAR_TASK_NAME, SOURCESJAR_TASK_NAME)

    project.artifacts {
      archives project.sourcesJar
      archives project.javadocJar
    }
  }

  private void checkForJavaPlugin() {
    if(!project.plugins.hasPlugin('java')) {
      throw new InvalidUserDataException("You must apply the 'java' or 'java-base' plugin to use the 'java-extras' plugin.")
    }
  }

  private void addJavadocJarTask() {
    project.tasks.add(name: JAVADOCJAR_TASK_NAME, type: Jar) {
      classifier = 'javadoc'
      from project.javadoc.outputs.files
      group 'documentation'
      description 'Create a Javadoc documentation jar'
      dependsOn 'javadoc'
    }
  }

  private void addSourceJarTask() {
    project.tasks.add(name: SOURCESJAR_TASK_NAME, type: Jar) {
      from project.sourceSets.main.allSource
      classifier = 'sources'
      group 'documentation'
      description 'Create a sources jar'
    }
  }

}