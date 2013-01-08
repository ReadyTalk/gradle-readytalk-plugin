package com.readytalk.gradle.plugins

import spock.lang.*

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.gradle.api.InvalidUserDataException

import com.readytalk.gradle.plugins.GroovyExtrasPlugin

class GroovyExtrasPluginTest extends Specification {
  
  Project setupProject() {
    Project project = ProjectBuilder.builder().build()
    project.apply(plugin: 'groovy')
    project.apply(plugin: 'groovy-extras')
    return project
  }

  def 'apply to project with groovy plugin'() {
    given:
    Project project = setupProject()

    expect:
    project.plugins.hasPlugin(GroovyExtrasPlugin)
  }

  def 'apply to project without groovy plugin'() {
    given:
    Project project = ProjectBuilder.builder().build()

    when:
    project.apply(plugin: 'groovy-extras')

    then:
    thrown(InvalidUserDataException)     
  }
}