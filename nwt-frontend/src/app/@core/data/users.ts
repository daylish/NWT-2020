import { Observable } from 'rxjs';

export interface Project {
  id: number;
  name: string;
}

export interface SlackConfiguration {
  apiKey: string;
  messageTemplate: string;
  channelName: string;
  enabled: boolean;
}

export interface LoggedUser {
  user: User;
}

export interface Client {
  id: number;
  name: string;
}

export interface User {
  id: number;
  picture: string;

  firstName: string;
  lastName: string;
  username: string;
  email: string;
  password: string; // always null, except when creating/updating

  userTypes: UserType[];
  accessibleProjectIds?: number[];
  client: Client;
}

export interface UserType {
  userTypeId: number;
  name: string;
  privileges: UserPrivilege[];
}

export const USER_PRIVILEGE_SUBSCRIPTIONS_VIEW = 70;

export const USER_PRIVILEGE_POLICIES_VIEW = 60;
export const USER_PRIVILEGE_POLICIES_CREATE = 61;
export const USER_PRIVILEGE_POLICIES_EDIT = 62;
export const USER_PRIVILEGE_POLICIES_DELETE = 63;

export const USER_PRIVILEGE_USER_TYPE_VIEW = 0;
export const USER_PRIVILEGE_USER_TYPE_CREATE = 1;
export const USER_PRIVILEGE_USER_TYPE_EDIT = 2;
export const USER_PRIVILEGE_USER_TYPE_DELETE = 3;

export const USER_PRIVILEGE_USER_VIEW = 10;
export const USER_PRIVILEGE_USER_CREATE = 11;
export const USER_PRIVILEGE_USER_EDIT = 12;
export const USER_PRIVILEGE_USER_DELETE = 13;

export const USER_PRIVILEGE_PROJECT_VIEW = 20;
export const USER_PRIVILEGE_PROJECT_CREATE = 21;
export const USER_PRIVILEGE_PROJECT_EDIT = 22;
export const USER_PRIVILEGE_PROJECT_DELETE = 23;

export const USER_PRIVILEGE_ENVIRONMENT_VIEW = 30;
export const USER_PRIVILEGE_ENVIRONMENT_CREATE = 31;
export const USER_PRIVILEGE_ENVIRONMENT_EDIT = 32;
export const USER_PRIVILEGE_ENVIRONMENT_DELETE = 33;

export const USER_PRIVILEGE_CHANGELOG_VIEW = 40;
export const USER_PRIVILEGE_CHANGELOG_CREATE = 41;
export const USER_PRIVILEGE_CHANGELOG_DELETE = 42;
export const USER_PRIVILEGE_CHANGELOG_EXPORT = 43;

export const USER_PRIVILEGE_SLACK_INTEGRATION = 50;
export const USER_PRIVILEGE_MS_TEAMS_INTEGRATION = 51;
export const USER_PRIVILEGE_JENKINS_INTEGRATION = 52;

export const USER_PRIVILEGE_DEPLOYMENT_VIEW = 60;
export const USER_PRIVILEGE_DEPLOYMENT_CREATE = 61;
export const USER_PRIVILEGE_DEPLOYMENT_DELETE = 62;

export const USER_PRIVILEGE_REPORT_VIEW = 60;
export const USER_PRIVILEGE_REPORT_CREATE = 61;
export const USER_PRIVILEGE_REPORT_EXPORT = 62;


export interface UserPrivilege {
  userPrivilegeId: number;
  name: string;
  code: string;
  summary: string;
  category: string;
}

export interface Service {
  id: number;
  name: string;
  description: string;
  mandatory: boolean;
  deprecated: boolean;
}

export interface ServicePlan {
  id: number;
  name: string;
  deprecated: boolean;
  price: number;
  billingPeriod: string;
  services: Service[];
}

export interface ServiceSubscription {
  id: number;
  clientId: number;
  validityFrom: Date;
  validityTo: Date;
  service: Service;
  servicePlan: ServicePlan;
}




export interface Contacts {
  user: User;
  type: string;
}

export interface RecentUsers extends Contacts {
  time: number;
}

export abstract class UserData {
  abstract getUsers(): Observable<User[]>;
  abstract getContacts(): Observable<Contacts[]>;
  abstract getRecentUsers(): Observable<RecentUsers[]>;
}
