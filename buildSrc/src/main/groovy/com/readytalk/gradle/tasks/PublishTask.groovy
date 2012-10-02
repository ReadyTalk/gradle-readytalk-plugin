package com.readytalk.gradle.tasks

import org.gradle.api.Project
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.OutputDirectory

import com.readytalk.gradle.publishers.OpenSourcePublisherPlugin

class PublishTask extends DefaultTask {

  @TaskAction
  void run() {

    project.uploadArchives.repositories {
      add project.repositories.getByName(OpenSourcePublisherPlugin.getSnapshotRepoName())
    }

    project.uploadArchives.execute()
  }
}