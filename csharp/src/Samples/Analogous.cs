namespace Samples
{
    using System;
    using System.Collections.Generic;
    using System.Linq;

    public static class Analogous
    {
        public static IEnumerable<Sport> GetFriendsSportsLegacy(this User user)
        {
            var result = new SortedSet<Sport>(new SportComparer());
            foreach (var userFriend in user.Friends)
            foreach (var userFriendSport in userFriend.Sports)
                result.Add(userFriendSport);
            return result;
        }

        public static IEnumerable<Sport> GetFriendsSports(this User user)
        {
            return user.Friends.SelectMany(x => x.Sports).Distinct(new SportEqualityComparer()).OrderBy(x => x.Name, StringComparer.Ordinal);
        }
    }

    public class Sport
    {
        public Sport(string name, string description)
        {
            this.Description = description;
            this.Name = name;
        }

        public string Description { get; }

        public string Name { get; }
    }

    public class SportComparer : IComparer<Sport>
    {
        public int Compare(Sport x, Sport y)
        {
            return StringComparer.Ordinal.Compare(x.Name, y.Name);
        }
    }

    public class SportEqualityComparer : IEqualityComparer<Sport>
    {
        public bool Equals(Sport x, Sport y)
        {
            return x.Name == y.Name;
        }

        public int GetHashCode(Sport obj)
        {
            return obj.Name.GetHashCode();
        }
    }

    public class User
    {
        public User(string id, string login, List<Sport> sports, List<User> friends)
        {
            this.Id = id;
            this.Login = login;
            this.Sports = sports;
            this.Friends = friends;
        }

        public List<User> Friends { get; }

        public string Id { get; }

        public string Login { get; }

        public List<Sport> Sports { get; }
    }
}
