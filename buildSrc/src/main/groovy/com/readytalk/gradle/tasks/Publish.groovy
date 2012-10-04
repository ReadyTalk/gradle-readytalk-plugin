package com.readytalk.gradle.tasks

import org.gradle.api.Project
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.OutputDirectory

import org.gradle.api.artifacts.repositories.ArtifactRepository

class Publish extends DefaultTask {

  ArtifactRepository repo

  @TaskAction
  void run() {

    project.uploadArchives.repositories {
      add repo
    }

    project.uploadArchives.execute()
  }

  void setRepo(ArtifactRepository repo) {
  	this.repo = repo
  }

  ArtifactRepository getRepo() {
  	return repo
  }

}