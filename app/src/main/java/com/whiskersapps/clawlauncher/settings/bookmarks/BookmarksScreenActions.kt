package com.whiskersapps.clawlauncher.settings.bookmarks

import com.whiskersapps.clawlauncher.shared.model.Bookmark
import com.whiskersapps.clawlauncher.shared.model.Group

sealed class BookmarksScreenAction {
    data object NavigateBack : BookmarksScreenAction()

    data class UpdateSelectedTab(val tab: Int) : BookmarksScreenAction()

    data class OpenAddDialog(val page: Int) : BookmarksScreenAction()

    data class OpenEditBookmarkDialog(val bookmark: Bookmark) : BookmarksScreenAction()

    data class OpenEditGroupDialog(val group: Group) : BookmarksScreenAction()

    data object CloseAddBookmarkDialog : BookmarksScreenAction()

    data object CloseAddGroupDialog : BookmarksScreenAction()

    data object CloseEditBookmarkDialog : BookmarksScreenAction()

    data class UpdateAddBookmarkDialogFields(val name: String, val url: String) :
        BookmarksScreenAction()

    data class UpdateAddGroupDialogFields(val name: String) : BookmarksScreenAction()

    data class UpdateEditBookmarkDialogFields(val dialog: BookmarksScreenState.EditBookmarkDialog) :
        BookmarksScreenAction()

    data object AddBookmark : BookmarksScreenAction()

    data object AddGroup : BookmarksScreenAction()

    data object EditBookmark : BookmarksScreenAction()

    data object DeleteBookmark : BookmarksScreenAction()

    data class ChangeAddGroupBookmarkSelection(val id: Int, val selected: Boolean) :
        BookmarksScreenAction()

    data object CloseEditGroupDialog : BookmarksScreenAction()

    data class UpdateEditGroupDialogFields(val name: String) : BookmarksScreenAction()

    data class ChangeEditGroupBookmarkSelection(val id: Int, val selected: Boolean) :
        BookmarksScreenAction()

    data object SaveGroupEdit : BookmarksScreenAction()

    data object DeleteGroup : BookmarksScreenAction()
}