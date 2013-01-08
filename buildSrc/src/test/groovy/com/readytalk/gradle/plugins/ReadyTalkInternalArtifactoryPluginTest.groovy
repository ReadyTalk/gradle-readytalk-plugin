package com.readytalk.gradle.plugins

import spock.lang.*

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.gradle.api.InvalidUserDataException

class ReadyTalkInternalPluginTest extends Specification {
  
  Project setupProject() {
    Project project = ProjectBuilder.builder().build()
    project.setProperty('type', 'plugin')
    project.setProperty('artifactory_root', 'https')
    project.setProperty('artifactory_main', 'https')
    project.setProperty('artifactory_username', 'sgoings')
    project.setProperty('artifactory_password', 'secret')

    project.apply(plugin: 'readytalk-internal-artifactory')

    return project
  }

  def 'apply to project'() {
    given:
    Project project = setupProject()

    expect:
    project.plugins.hasPlugin('ivy-publish')
  }
}