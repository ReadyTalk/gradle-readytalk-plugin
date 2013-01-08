package com.readytalk.gradle.plugins

import spock.lang.*

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.gradle.api.InvalidUserDataException

import com.readytalk.gradle.plugins.ReadyTalkPlugin

class ReadyTalkPluginTest extends Specification {
  
  Project setupProject() {
    Project project = ProjectBuilder.builder().build()
    project.apply(plugin: 'processor')
    return project
  }

  def 'apply to project'() {
    given:
    Project project = setupProject()

    expect:
    project.plugins.hasPlugin(ReadyTalkPlugin)
  }
}