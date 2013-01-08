package com.readytalk.gradle.plugins

import spock.lang.*

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.gradle.api.InvalidUserDataException

import com.readytalk.gradle.plugins.JavaExtrasPlugin

class JavaExtrasPluginTest extends Specification {
  
  Project setupProject() {
    Project project = ProjectBuilder.builder().build()
    project.apply(plugin: 'java')
    project.apply(plugin: 'java-extras')
    return project
  }

  def 'apply to project with java plugin'() {
    given:
    Project project = setupProject()

    expect:
    project.plugins.hasPlugin(JavaExtrasPlugin)
  }

  def 'apply to project without java-base plugin'() {
    given:
    Project project = ProjectBuilder.builder().build()

    when:
    project.apply(plugin: 'java-extras')

    then:
    thrown(InvalidUserDataException)     
  }
}