package com.readytalk.gradle.publishers

import com.readytalk.gradle.tasks.InstallTask
import org.gradle.api.Project
import org.gradle.api.Plugin
import org.gradle.api.Task

import org.gradle.api.DefaultTask

class ReadyTalkPublisherPlugin extends LocalPublisherPlugin implements PublisherConvention {

  void apply(Project project) {
    super.apply(project)

    project.apply {
      plugin 'artifactory'
    }

    addPublishTask(DefaultTask.class)
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