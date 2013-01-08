package com.readytalk.gradle.extensions

import org.gradle.api.Project

class ReadyTalkExtension {
  private final Project project

  String type
  Closure packaging
  Closure credentials

  ReadyTalkExtension(Project target) {
    this.project = target
  }

}
