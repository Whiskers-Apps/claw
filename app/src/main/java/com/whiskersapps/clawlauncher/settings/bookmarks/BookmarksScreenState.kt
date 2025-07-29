package com.whiskersapps.clawlauncher.settings.bookmarks

import com.whiskersapps.clawlauncher.shared.model.Bookmark
import com.whiskersapps.clawlauncher.shared.model.Group

data class BookmarksScreenState(
    val loading: Boolean = true,
    val bookmarks: List<Bookmark> = emptyList(),
    val groups: List<Group> = emptyList(),
    val showAddBookmarkDialog: Boolean = false,
    val showEditBookmarkDialog: Boolean = false,
    val showAddGroupDialog: Boolean = false,
    val showEditGroupDialog: Boolean = false,
    val addBookmarkDialog: AddBookmarkDialog = AddBookmarkDialog(),
    val addGroupDialog: AddGroupDialog = AddGroupDialog(),
    val editGroupDialog: EditGroupDialog = EditGroupDialog(),
    val editBookmarkDialog: EditBookmarkDialog = EditBookmarkDialog()
) {
    data class AddBookmarkDialog(
        val name: String = "",
        val url: String = ""
    )

    data class EditBookmarkDialog(
        val bookmark: Bookmark = Bookmark(-1, "", ""),
        val name: String = "",
        val url: String = ""
    )

    data class AddGroupDialog(
        val name: String = "",
        val bookmarks: List<GroupBookmark> = emptyList()
    )

    data class EditGroupDialog(
        val id: Int = -1,
        val name: String = "",
        val bookmarks: List<GroupBookmark> = emptyList()
    )

    data class GroupBookmark(
        val selected: Boolean = false,
        val bookmark: Bookmark = Bookmark(-1, "", "")
    )
}
