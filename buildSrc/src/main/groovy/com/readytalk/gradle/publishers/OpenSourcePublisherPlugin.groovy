package com.readytalk.gradle.publishers

import com.readytalk.gradle.tasks.PublishTask
import org.gradle.api.Project
import org.gradle.api.Plugin
import org.gradle.api.DefaultTask
import org.gradle.api.Task

class OpenSourcePublisherPlugin extends LocalPublisherPlugin implements PublisherConvention {

  void apply(Project project) {
    super.apply(project)

    addPublishTask(PublishTask.class)
  }

  void addPublishTask(Class<Task> task) {
    PublishTask publish = project.tasks.add('publish', task)
    publish.setDescription 'Install project into "Maven Central"'
    publish.setGroup 'publish'
  }

}