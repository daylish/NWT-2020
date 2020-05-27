export interface List {
  listID: number;
  userID: number;
  title: string;
  itemsList: ListItem[];
}

export interface ListItem {
  listItemID: number;
  listItemStatus: string;
  itemId: number;
}
