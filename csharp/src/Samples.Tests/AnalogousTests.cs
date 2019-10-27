namespace Samples.Tests
{
    using System.Collections.Generic;
    using System.Linq;

    using FsCheck;
    using FsCheck.Xunit;

    using Xunit;
    using Xunit.Abstractions;

    public class AnalogousTests
    {
        private readonly ITestOutputHelper output;

        public AnalogousTests(ITestOutputHelper output)
        {
            this.output = output;
        }

        [Property(Arbitrary = new[] { typeof(UserArbitrary) }, Verbose = true, MaxTest = 1000)]
        public void Analogous(User anyUser)
        {
            this.output.WriteLine(anyUser.ToString());
            Assert.Equal(anyUser.GetFriendsSportsLegacy(), anyUser.GetFriendsSports());
        }
    }

    public static class SportArbitrary
    {
        static SportArbitrary()
        {
            Arb.Register(typeof(SportArbitrary));
        }

        public static Arbitrary<Sport> Generate()
        {
            var results =
                from name in Arb.Default.NonNull<string>().Generator
                from description in Arb.Default.String().Generator
                select new Sport(name.Get, description);
            return Arb.From(results);
        }
    }

    public static class UserArbitrary
    {
        static UserArbitrary()
        {
            Arb.Register(typeof(UserArbitrary));
        }

        public static Arbitrary<User> Generate()
        {
            var result =
                from strings in Arb.Default.NonNull<string>().Generator.Two()
                from integers in Arb.Default.PositiveInt().Generator.Where(x => x.Get <= 10).Two()
                from sports in SportArbitrary.Generate().Generator.ListOf(integers.Item2.Get)
                from friends in FriendArbitrary.Generate().Generator.ListOf(integers.Item2.Get)
                select new User(strings.Item1.Get, strings.Item2.Get, sports.ToList(), friends.ToList());
            return Arb.From(result);
        }
    }

    public static class FriendArbitrary
    {
        static FriendArbitrary()
        {
            Arb.Register(typeof(FriendArbitrary));
        }

        public static Arbitrary<User> Generate()
        {
            var result =
                from strings in Arb.Default.NonNull<string>().Generator.Two()
                from integers in Arb.Default.PositiveInt().Generator.Where(x => x.Get <= 10).Two()
                from sports in SportArbitrary.Generate().Generator.ListOf(integers.Item2.Get)
                select new User(strings.Item1.Get, strings.Item2.Get, sports.ToList(), new List<User>());
            return Arb.From(result);
        }
    }
}
