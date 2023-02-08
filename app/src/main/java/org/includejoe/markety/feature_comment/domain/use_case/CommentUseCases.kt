package org.includejoe.markety.feature_comment.domain.use_case

data class CommentUseCases(
    val createComment: CreateCommentUseCase,
    val replyComment: ReplyCommentUseCase,
    val likeComment: LikeCommentUseCase,
    val getPostComments: GetPostCommentsUseCase,
    val deleteComment: DeleteCommentUseCase,
)
