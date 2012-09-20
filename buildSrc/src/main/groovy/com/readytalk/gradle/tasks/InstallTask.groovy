package com.readytalk.gradle.tasks

import org.gradle.api.Project
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.InputDirectory;
import org.gradle.api.tasks.OutputDirectory;

class InstallTask extends DefaultTask {

  @TaskAction
  void run() {
    project.uploadArchives {
      repositories {
        add project.buildscript.repositories.getByName('ecovate_init_local')
      }
    }
    project.uploadArchives.execute()
  }

}