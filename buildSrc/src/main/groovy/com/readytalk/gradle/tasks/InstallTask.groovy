package com.readytalk.gradle.tasks

import org.gradle.api.Project
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.InputDirectory;
import org.gradle.api.tasks.OutputDirectory;

class InstallTask extends DefaultTask {

  @TaskAction
  void run() {

  	def localRepo = project.uploadArchives.repositories.getByName('readytalk_local')
  	project.uploadArchives.repositories.clear()
  	project.uploadArchives.repositories.add(localRepo)

    project.uploadArchives.execute()
  }

}