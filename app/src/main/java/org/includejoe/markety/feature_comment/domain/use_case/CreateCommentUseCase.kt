package org.includejoe.markety.feature_comment.domain.use_case

import org.includejoe.markety.feature_comment.domain.repository.CommentRepository
import javax.inject.Inject

class CreateCommentUseCase @Inject constructor(
    private val repository: CommentRepository
) {
}