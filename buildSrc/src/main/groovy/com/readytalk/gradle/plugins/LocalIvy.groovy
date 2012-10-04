package com.readytalk.gradle.plugins

import org.gradle.api.Project
import org.gradle.api.Plugin
import org.gradle.api.DefaultTask
import org.gradle.api.Task
import org.gradle.api.artifacts.repositories.IvyArtifactRepository

import com.readytalk.gradle.tasks.Publish

class LocalIvy implements Plugin<Project> {

  static final String INSTALL_TASK_NAME = 'install'
  static final String REPO_NAME = 'local_ivy'

  private Project project
  private IvyArtifactRepository repo

  void apply(Project target) {
    project = target

    createRepo()

    addRepoToRepositories()
    addInstallTask()
  }

  private void addInstallTask() {
    project.tasks.add(name: INSTALL_TASK_NAME, type: Publish) { 
      description "Install project into the local Ivy repository"
      group 'publish'
      dependsOn 'build'
      setRepo this.repo
    }
  }

  private void addRepoToRepositories() {
    project.repositories {
      add repo
    }
  }

  private void createRepo() {
    repo = project.repositories.ivy { 
      name REPO_NAME
      layout 'maven'
      url System.getProperty('user.home') + '/.ivy2/local'
    }
  }

}