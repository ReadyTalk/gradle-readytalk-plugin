package com.readytalk.gradle

import com.readytalk.gradle.tasks.InstallTask
import com.readytalk.gradle.util.PluginApplicator

import org.gradle.api.DefaultTask

class ReadyTalkPublisherPlugin extends PluginApplicator implements ReadyTalkPublishConvention {

  void apply(Project project) {
    super.apply(project)

    project.apply {
      plugin 'artifactory'
    }

    addInstallTask(InstallTask.class)
    addPublishTask(DefaultTask.class)
  }

  void addInstallTask(Class<Task> task) {
  	InstallTask install = project.tasks.add('install', task)
  	install.setDescription 'Install project into the local Ivy repository'
  	install.setGroup 'publish'
    install.dependsOn 'build'
  }

  void addPublishTask(Class<Task> task) {
    // The artifactoryPublish looks for itself in the task graph,
    // so we have to define it like this
    DefaultTask publish = project.tasks.add('publish', task)
    publish.setDescription 'Install project into corporate Artifactory'
    publish.setGroup 'publish'
    publish.dependsOn 'artifactoryPublish'
  }

}